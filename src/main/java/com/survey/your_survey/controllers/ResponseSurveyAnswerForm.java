package com.survey.your_survey.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSurveyAnswerForm {
    private String title;
    private List<ResponseQuestionForm> questions;
}
