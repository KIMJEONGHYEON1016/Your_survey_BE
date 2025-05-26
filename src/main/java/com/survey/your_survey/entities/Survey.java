package com.survey.your_survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(indexes = @Index(name = "idx_survey_created", columnList = "createdAt DESC"))
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 설문 제목 */
    @Column(nullable = false)
    private String title;

    /** 응답자용 링크 토큰 (UUID 등) */
    @Column(nullable = false, unique = true, length = 36)
    private String responseToken;

    /** 설문자용 링크 토큰 (UUID 등) */
    @Column(nullable = false, unique = true, length = 36)
    private String ownerToken;

    /** 설문 생성 시각 */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /** 설문에 포함된 질문들 */
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;
}

