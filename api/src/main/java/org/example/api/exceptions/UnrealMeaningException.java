package org.example.api.exceptions;

public class UnrealMeaningException extends IllegalArgumentException {
    public UnrealMeaningException(String msg) {
        super("Значение: " + msg + " не соответствует ожидаемому числу");
    }
}