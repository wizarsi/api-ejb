package org.example.ejb.model;


import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
