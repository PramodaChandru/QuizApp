package com.sample.quiz_service.feing;

import com.sample.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<Map> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQ);

    @PostMapping("question/getQuestions")
    public ResponseEntity<Map> getQuestions(@RequestBody List<Integer> ids);

    @PostMapping("question/getScore")
    public ResponseEntity<Map> calculateResult(@RequestBody List<Response> responses);
}
