package com.survey.your_survey.repositories;

import com.survey.your_survey.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {}
