package org.example.api.model.dto.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private String name;
    private String passportID;
    private String nationality;
    private Location location;
}
