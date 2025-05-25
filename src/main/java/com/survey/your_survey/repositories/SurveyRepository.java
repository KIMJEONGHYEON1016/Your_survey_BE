package com.survey.your_survey.repositories;

import com.survey.your_survey.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Optional<Survey> findByResponseToken(String responseToken);
    Optional<Survey> findByOwnerToken(String ownerToken);
}
