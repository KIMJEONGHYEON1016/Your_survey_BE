package com.survey.your_survey.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 스프링 빈(Bean)으로 등록할 여러 가지 객체를 설정하는 클래스입니다.
 * @Configuration 애너테이션을 사용하여 스프링의 설정 클래스로 지정합니다.
 */
@Configuration
@RequiredArgsConstructor  // final 필드를 매개변수로 하는 생성자를 자동 생성해주는 Lombok 애너테이션
public class BeanConfig {

    // EntityManager는 JPA에서 데이터베이스와 상호작용하기 위한 객체입니다.
    private final EntityManager em;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // ✅ 기본 생성자 사용
    }

    /**
     * ObjectMapper 빈을 생성합니다.
     * ObjectMapper는 JSON을 객체로 변환하거나 객체를 JSON으로 변환하는 역할을 합니다.
     * Java 8의 날짜 및 시간 API(LocalDateTime 등)를 지원하기 위해 JavaTimeModule을 등록합니다.
     */
    @Lazy
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());  // Java 8의 날짜 및 시간 API를 지원하도록 설정

        return om;
    }


    /**
     * ModelMapper 빈을 생성합니다.
     * ModelMapper는 객체 간 변환(예: DTO ↔ Entity)을 편리하게 수행할 수 있도록 도와주는 라이브러리입니다.
     * STRICT 매칭 전략을 사용하여 필드명이 정확하게 일치해야만 매핑되도록 설정합니다.
     */
    @Lazy
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
