package com.survey.your_survey.rests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiRequest {

    private final RestTemplate restTemplate;
    private final ObjectMapper om;

    private ResponseEntity<String> response;

    public ApiRequest request(String url, HttpMethod method, Object data) {
        method = method == null ? HttpMethod.GET : method;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = null;
        try {
            if (data != null) {
                String body = om.writeValueAsString(data);
                requestEntity = new HttpEntity<>(body, headers);
            } else {
                requestEntity = new HttpEntity<>(headers);
            }
            this.response = restTemplate.exchange(URI.create(url), method, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public ResponseEntity<String> getResponse() {
        return response;
    }

    public HttpStatusCode getStatus() {
        return response.getStatusCode();
    }

    public HttpHeaders getHeaders() {
        return response.getHeaders();
    }

    public String getBodyAsString() {
        return response.getBody();
    }

    public <T> T getBodyAsObject(Class<T> clazz) {
        try {
            return om.readValue(response.getBody(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> getBodyAsList(TypeReference<List<T>> typeReference) {
        try {
            return om.readValue(response.getBody(), typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
