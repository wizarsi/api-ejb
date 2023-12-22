package org.example.ejb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private Double x;
    private long y;
    private double z;
}
