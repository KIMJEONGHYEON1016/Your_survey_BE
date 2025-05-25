package com.survey.your_survey.controllers;

import com.survey.your_survey.constants.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * 설문 내 개별 질문을 생성할 때 사용하는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestQuestionCreate {

    /** 질문 내용 */
    @NotBlank(message = "질문 내용은 필수입니다.")
    private String content;

    /** 질문 유형 (SHORT_TEXT / MULTIPLE_CHOICE) */
    @NotNull(message = "질문 유형은 필수입니다.")
    private QuestionType type;

    /** 객관식 보기 항목 (객관식일 경우 필수, 단답형일 경우 null 허용) */
    private List<String> options;

    /** 복수 선택 허용 여부 (객관식일 경우만 의미 있음) */
    private Boolean allowMultiple = false;

    /** 복수 선택 허용 시 선택 가능 최대 수 (null이면 제한 없음) */
    private Integer multipleLimit;
}
