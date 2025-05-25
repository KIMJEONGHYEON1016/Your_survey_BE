package com.survey.your_survey.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSurveyCreate {

    private String responseUrl; // 응답자용 링크
    private String ownerUrl;    // 설문자(생성자)용 링크
}
