package com.sample.quiz_service.service;

import com.sample.quiz_service.dao.QuizDao;
import com.sample.quiz_service.feing.QuizInterface;
import com.sample.quiz_service.model.QuestionWrapper;
import com.sample.quiz_service.model.Quiz;
import com.sample.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<Map> create(String title, String category, int numQ) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            List<Integer> questions = (List<Integer>) quizInterface.getQuestionsForQuiz(category, numQ).getBody().get("data");
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            quizDao.save(quiz);
            map.put("status", 1);
            map.put("message", "Quiz " + title + "created successfully");
            return new ResponseEntity<>(map, CREATED);
        } catch (Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "failed to create quiz");
            return new ResponseEntity<>(map, BAD_REQUEST);
        }
    }

    public ResponseEntity<Map> getQuiz(Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Integer> questionIds = quiz.getQuestionIds();
            List<QuestionWrapper> questionForUser = (List<QuestionWrapper>) quizInterface.getQuestions(questionIds).getBody().get("data");
            map.put("status", 1);
            map.put("data", questionForUser);
            return new ResponseEntity<>(map, OK);
        } catch (Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "data not found");
            return new ResponseEntity<>(map, BAD_REQUEST);
        }
    }

    public ResponseEntity<Map> calculateResult(Integer id, List<Response> responses) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", 1);
        map.put("result", quizInterface.calculateResult(responses).getBody().get("result"));
        return new ResponseEntity<>(map, OK);
    }
}
