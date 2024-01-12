package org.example.api.model.dto.rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyGroupBase {
    private String name;
    private Coordinates coordinates;
    private Long studentsCount;
    private int shouldBeExpelled;
    private String formOfEducation;
    private String semesterEnum;
    private Person groupAdmin;
}
