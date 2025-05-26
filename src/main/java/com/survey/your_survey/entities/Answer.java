package com.survey.your_survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.survey.your_survey.entities.AnswerDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(indexes = @Index(name = "idx_answer_survey", columnList = "survey_id"))
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 응답 제출 시각 */
    @CreationTimestamp
    private LocalDateTime submittedAt;

    /** 어떤 설문에 대한 응답인지 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    /** 응답한 세부 항목들 */
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerDetail> details;
}