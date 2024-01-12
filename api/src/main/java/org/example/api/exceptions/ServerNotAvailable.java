package org.example.api.exceptions;


import org.example.api.model.dto.rest.ErrorDTO;

public class ServerNotAvailable extends RuntimeException {
    ErrorDTO errorDTO;
    public ServerNotAvailable(ErrorDTO errorDTO) {
        this.errorDTO=errorDTO;
    }

}
