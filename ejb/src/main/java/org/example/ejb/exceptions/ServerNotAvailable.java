package org.example.ejb.exceptions;


import org.example.ejb.model.ErrorDTO;

public class ServerNotAvailable extends RuntimeException {
    ErrorDTO errorDTO;
    public ServerNotAvailable() {
        super("asd");
    }
    public ServerNotAvailable(ErrorDTO errorDTO) {
        this.errorDTO=errorDTO;
    }

}
