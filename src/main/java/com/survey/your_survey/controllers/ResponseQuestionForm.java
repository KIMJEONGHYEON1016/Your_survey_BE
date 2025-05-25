package com.survey.your_survey.controllers;

import com.survey.your_survey.constants.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseQuestionForm {
    private Long id;
    private String content;
    private QuestionType type;
    private List<String> options;
    private Boolean allowMultiple;
    private Integer multipleLimit;
}
