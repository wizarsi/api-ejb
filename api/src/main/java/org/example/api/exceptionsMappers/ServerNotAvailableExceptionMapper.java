package org.example.api.exceptionsMappers;



import org.example.api.model.ErrorData;
import org.example.ejb.exceptions.ServerNotAvailable;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerNotAvailableExceptionMapper implements ExceptionMapper<ServerNotAvailable> {

    public Response toResponse(ServerNotAvailable ex) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(new ErrorData("Service unavailable")).build();
    }
}
