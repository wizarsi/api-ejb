package org.example.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorData {
    @JsonbTransient
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    private String message;
    private String time;

    public ErrorData(String message) {
        this.message = message;

        this.time = ZonedDateTime.now().format(ErrorData.formatter);
        this.time = this.time.substring(0,this.time.length()-5-1) + "Z";
    }
}
