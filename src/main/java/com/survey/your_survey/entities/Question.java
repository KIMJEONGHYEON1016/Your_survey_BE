package com.survey.your_survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.survey.your_survey.constants.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(indexes = @Index(name = "idx_question_survey", columnList = "survey_id"))
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 질문 내용 */
    @Column(nullable = false)
    private String content;

    /** 질문 타입 (예: SHORT_TEXT, MULTIPLE_CHOICE) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    /** 객관식 보기 목록 (단답형일 경우 null) */
    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_value")
    private List<String> options;

    /** 복수 선택 허용 여부 (객관식일 경우만 적용) */
    @Column(nullable = false)
    private boolean allowMultiple = false;

    /** 선택 가능 최대 개수 (null이면 제한 없음) */
    private Integer multipleLimit;

    /** 이 질문이 속한 설문 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;
}
