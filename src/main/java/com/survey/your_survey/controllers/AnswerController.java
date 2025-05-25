package com.survey.your_survey.controllers;

import com.survey.your_survey.entities.*;
import com.survey.your_survey.repositories.*;
import com.survey.your_survey.rests.JSONData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey/respond")
@RequiredArgsConstructor
public class AnswerController {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerDetailRepository answerDetailRepository;

    @PostMapping("/{responseToken}")
    public JSONData submitAnswer(
            @PathVariable("responseToken") String responseToken,
            @RequestBody @Valid RequestSurveyAnswer request
    ) {
        // 설문 조회
        Survey survey = surveyRepository.findByResponseToken(responseToken)
                .orElseThrow(() -> new RuntimeException("해당 설문을 찾을 수 없습니다."));

        // 응답 저장
        Answer answer = Answer.builder()
                .survey(survey)
                .build();
        answerRepository.save(answer);

        // 응답 상세 저장
        List<AnswerDetail> details = request.getAnswers().stream()
                .flatMap(dto -> {
                    Question question = questionRepository.findById(dto.getQuestionId())
                            .orElseThrow(() -> new RuntimeException("질문 ID 오류: " + dto.getQuestionId()));

                    // 다중 응답인지 단일 응답인지 판별
                    List<String> responses;
                    if (dto.getResponse() instanceof List) {
                        responses = ((List<?>) dto.getResponse()).stream()
                                .map(Object::toString)
                                .toList();
                    } else {
                        responses = List.of(dto.getResponse().toString());
                    }

                    return responses.stream().map(res ->
                            AnswerDetail.builder()
                                    .question(question)
                                    .answer(answer)
                                    .response(res)
                                    .build());
                })
                .toList();

        answerDetailRepository.saveAll(details);

        return new JSONData("응답이 저장되었습니다.");
    }


    @GetMapping("/{responseToken}")
    public JSONData getSurveyForResponse(@PathVariable("responseToken") String responseToken) {
        Survey survey = surveyRepository.findByResponseToken(responseToken)
                .orElseThrow(() -> new RuntimeException("설문을 찾을 수 없습니다."));

        List<ResponseQuestionForm> questions = survey.getQuestions().stream()
                .map(q -> ResponseQuestionForm.builder()
                        .id(q.getId())
                        .content(q.getContent())
                        .type(q.getType())
                        .options(q.getOptions())
                        .allowMultiple(q.isAllowMultiple())
                        .multipleLimit(q.getMultipleLimit())
                        .build())
                .toList();

        return new JSONData(ResponseSurveyAnswerForm.builder()
                .title(survey.getTitle())
                .questions(questions)
                .build());
    }

}
