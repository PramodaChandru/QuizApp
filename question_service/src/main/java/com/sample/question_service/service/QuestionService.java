package com.sample.question_service.service;

import com.sample.question_service.dao.QuestionDao;
import com.sample.question_service.model.QuestionWrapper;
import com.sample.question_service.model.Questions;
import com.sample.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<Map> getAllQuestions() {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            List<Questions> questions = questionDao.findAll();
            map.put("status", 1);
            map.put("Data", questions);
            return new ResponseEntity<>(map, OK);
        } catch(Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, BAD_REQUEST);
        }
    }

    public ResponseEntity<Map> getQuestionsByCategory(String category) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            List<Questions> questions = questionDao.findByCategory(category);
            map.put("status", 1);
            map.put("Data by category", questions);
            return new ResponseEntity<>(map, OK);
        } catch(Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, BAD_REQUEST);
        }
    }

    public ResponseEntity<Map> saveQuestion(Questions questions) {
        Map<String, Object> map = new LinkedHashMap<>();
        questionDao.save(questions);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, CREATED);
    }

    public ResponseEntity<Map> deleteQuestion(int id) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            questionDao.deleteById(id);
            map.put("status", 1);
            map.put("message", "Record is deleted Successfully!");
            return new ResponseEntity<>(map, OK);
        } catch (Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "record is not found");
            return new ResponseEntity<>(map, NOT_FOUND);
        }
    }

    public ResponseEntity<Map> getQuestionsForQuiz(String categoryName, Integer numQ) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Integer> questions = questionDao.findRandomQuestionByCategory(categoryName, numQ);
        map.put("status", 1);
        map.put("data", questions);
        return new ResponseEntity<>(map, OK);
    }

    public ResponseEntity<Map> getQuestions(List<Integer> ids) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Questions> questions = new ArrayList<>();
        for(Integer id: ids) {
            questions.add(questionDao.findById(id).get());
        }
        for (Questions question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestion(question.getQuestion());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        map.put("status", 1);
        map.put("data", questionWrappers);
        return new ResponseEntity<>(map, OK);
    }

    public ResponseEntity<Map> calculateResult(List<Response> responses) {
        Map<String, Object> map = new LinkedHashMap<>();
        int result = 0;
        for (Response response : responses) {
            Questions questions = questionDao.findById(response.getId()).get();
            if (response.getUserAnswer().equals(questions.getCorrectAnswer())) {
                result++;
            }
        }
        map.put("status", 1);
        map.put("result", result);
        return new ResponseEntity<>(map, OK);
    }
}
