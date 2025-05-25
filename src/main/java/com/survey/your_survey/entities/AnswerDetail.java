package com.survey.your_survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(indexes = {
        @Index(name = "idx_answer_detail_answer", columnList = "answer_id"),
        @Index(name = "idx_answer_detail_question", columnList = "question_id")
})
public class AnswerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_detail_seq")
    @SequenceGenerator(name = "answer_detail_seq", sequenceName = "answer_detail_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @Column(nullable = false)
    private String response;
}
