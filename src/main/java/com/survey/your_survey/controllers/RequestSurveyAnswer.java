package com.survey.your_survey.controllers;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestSurveyAnswer {

    @NotNull(message = "응답 항목은 필수입니다.")
    @Size(min = 1, message = "최소 하나 이상의 응답이 필요합니다.")
    private List<RequestAnswerDetail> answers;
}
