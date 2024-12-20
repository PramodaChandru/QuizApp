package com.sample.quiz_service.controller;

import com.sample.quiz_service.model.QuizDto;
import com.sample.quiz_service.model.Response;
import com.sample.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<Map> create(@RequestBody QuizDto quizDto) {
        return quizService.create(quizDto.getTitle(), quizDto.getCategory(), quizDto.getNumQ());
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<Map> getQuiz(@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Map> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
