package com.survey.your_survey.controllers;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

/**
 * 설문 생성 요청을 받을 때 사용하는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestSurveyCreate {

    /** 설문 제목 */
    @NotBlank(message = "설문 제목은 필수입니다.")
    private String title;

    /** 포함될 질문 목록 */
    @NotEmpty(message = "질문은 하나 이상 포함되어야 합니다.")
    private List<RequestQuestionCreate> questions;
}