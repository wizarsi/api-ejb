package org.example.api.integration;

import org.example.api.model.dto.rest.StudyGroupBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.example.api.utils.Routes.URL_GET_ALL_GROUP;
import static org.example.api.utils.Routes.URL_GET_GROUP;

@Component
public class StudyGroupRestTemplateImpl implements StudyGroupRestTemplate {


    private final RestTemplate restTemplate;

    private final String BASE_URL;

    @Autowired
    public StudyGroupRestTemplateImpl(@Value("${service.first-service.url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        BASE_URL = baseUrl;
    }

    @Override
    public StudyGroupBase getGroupById(Long id) {
        System.out.println("homyak");
        System.out.println(id);
        ResponseEntity<StudyGroupBase> responseEntity = restTemplate.exchange(BASE_URL + URL_GET_GROUP, HttpMethod.GET, null, StudyGroupBase.class, id);
        return responseEntity.getBody();
    }

    @Override
    public List<StudyGroupBase> getAllStudyGroups() {
        ResponseEntity<List<StudyGroupBase>> responseEntity = restTemplate.exchange(BASE_URL + URL_GET_ALL_GROUP, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StudyGroupBase>>() {
                });
        return responseEntity.getBody();
    }

}
