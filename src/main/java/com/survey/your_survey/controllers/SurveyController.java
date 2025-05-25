package com.survey.your_survey.controllers;

import com.survey.your_survey.constants.QuestionType;
import com.survey.your_survey.entities.AnswerDetail;
import com.survey.your_survey.entities.Survey;
import com.survey.your_survey.entities.Question;
import com.survey.your_survey.repositories.AnswerDetailRepository;
import com.survey.your_survey.repositories.SurveyRepository;
import com.survey.your_survey.repositories.QuestionRepository;
import com.survey.your_survey.rests.JSONData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerDetailRepository answerDetailRepository;


    @PostMapping
    public JSONData createSurvey(@RequestBody @Valid RequestSurveyCreate request) {
        // 토큰 생성
        String responseToken = UUID.randomUUID().toString();
        String ownerToken = UUID.randomUUID().toString();

        // 설문 저장
        Survey survey = Survey.builder()
                .title(request.getTitle())
                .responseToken(responseToken)
                .ownerToken(ownerToken)
                .build();
        surveyRepository.save(survey);

        // 질문 저장
        List<Question> questions = request.getQuestions().stream().map(q ->
                Question.builder()
                        .content(q.getContent())
                        .type(q.getType())
                        .options(q.getOptions())
                        .allowMultiple(q.getAllowMultiple())
                        .multipleLimit(q.getMultipleLimit())
                        .survey(survey)
                        .build()
        ).toList();
        questionRepository.saveAll(questions);

        // 응답 객체 반환
        ResponseSurveyCreate response = ResponseSurveyCreate.builder()
                .responseUrl("/survey/respond/" + responseToken)
                .ownerUrl("/survey/manage/" + ownerToken)
                .build();

        return new JSONData(response);
    }

    @GetMapping("/manage/{ownerToken}")
    public JSONData getSurveyResults(@PathVariable("ownerToken") String ownerToken) {
        Survey survey = surveyRepository.findByOwnerToken(ownerToken)
                .orElseThrow(() -> new RuntimeException("설문을 찾을 수 없습니다."));

        List<ResponseQuestionResult> questionResults = survey.getQuestions().stream()
                .map(q -> {
                    List<AnswerDetail> details = answerDetailRepository.findByQuestion(q);

                    if (q.getType() == QuestionType.MULTIPLE_CHOICE) {
                        Map<String, Long> stats = q.getOptions().stream()
                                .collect(Collectors.toMap(
                                        opt -> opt,
                                        opt -> details.stream().filter(d -> opt.equals(d.getResponse())).count()
                                ));
                        return ResponseQuestionResult.builder()
                                .content(q.getContent())
                                .type(q.getType())
                                .optionStats(stats)
                                .build();
                    } else {
                        List<String> texts = details.stream()
                                .map(AnswerDetail::getResponse)
                                .toList();
                        return ResponseQuestionResult.builder()
                                .content(q.getContent())
                                .type(q.getType())
                                .shortAnswers(texts)
                                .build();
                    }
                }).toList();

        return new JSONData(ResponseSurveyResult.builder()
                .title(survey.getTitle())
                .questions(questionResults)
                .build());
    }

}
