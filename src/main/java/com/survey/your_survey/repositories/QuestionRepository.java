package com.survey.your_survey.repositories;
import com.survey.your_survey.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
