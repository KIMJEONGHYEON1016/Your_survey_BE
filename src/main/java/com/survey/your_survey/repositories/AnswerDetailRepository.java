package com.survey.your_survey.repositories;

import com.survey.your_survey.entities.AnswerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import com.survey.your_survey.entities.Question;

import java.util.List;

public interface AnswerDetailRepository extends JpaRepository<AnswerDetail, Long> {
    List<AnswerDetail> findByQuestion(Question question);
}
