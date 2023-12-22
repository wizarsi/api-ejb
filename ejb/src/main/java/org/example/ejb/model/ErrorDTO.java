package org.example.ejb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {
    private String message;
    private String dateTime;
    public ErrorDTO(String message){
        this.message=message;
        dateTime=getTime();
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
    private String getTime() {
        Date currentDate = new Date();

        // Создание формата даты и времени
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        // Преобразование даты и времени в строку в нужном формате
        return format.format(currentDate).replace("+0300","Z");

    }
}
