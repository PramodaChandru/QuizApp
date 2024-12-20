package com.sample.question_service.controller;

import com.sample.question_service.model.Questions;
import com.sample.question_service.model.Response;
import com.sample.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<Map> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<Map> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<Map> saveQuestion(@RequestBody Questions questions) {
        return questionService.saveQuestion(questions);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map> deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestion(id);
    }

    @GetMapping("generate")
    public ResponseEntity<Map> getQuestionsForQuiz(@RequestParam String categoryName,  @RequestParam Integer numQ) {
        return questionService.getQuestionsForQuiz(categoryName, numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<Map> getQuestions(@RequestBody List<Integer> ids) {
        return questionService.getQuestions(ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Map> calculateResult(@RequestBody List<Response> responses) {
        return questionService.calculateResult(responses);
    }
}
