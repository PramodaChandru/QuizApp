package com.sample.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numQ;
    String title;

}