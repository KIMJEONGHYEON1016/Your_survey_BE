package com.survey.your_survey.controllers;

import com.survey.your_survey.constants.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseQuestionResult {

    private String content;
    private QuestionType type;

    /** 객관식일 경우 각 보기별 응답 수 */
    private Map<String, Long> optionStats;

    /** 단답형일 경우 자유 응답 모음 */
    private List<String> shortAnswers;
}
