package org.example.api.controllers;

import org.example.api.utils.JndiUtils;
import org.example.ejb.model.CountExpelledDTO;
import org.example.ejb.service.IsuGroupService;

import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path(value = "/api")
@Stateless
public class IsuController {

    private IsuGroupService getService() {
        return JndiUtils.getFromContext(IsuGroupService.class,
                "ejb:/ejb-1.0-SNAPSHOT-jar-with-dependencies/IsuGroupServiceImpl!org.example.ejb.service.IsuGroupService");
    }

    @GET
    @Path("/isu/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok().entity("pong").build();
    }

    @GET
    @Path("/ejb/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ejbPing() {
        System.out.println("result----------------------");
        return Response.ok().entity(getService().ping()).build();
    }

    @GET
    @Path("/isu/group/{group-id}/expel-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response expelStudents(@PathParam("group-id") @Valid @Positive(message = "id должен быть положительным числом.") long groupId) {
        return Response.ok().entity(new CountExpelledDTO(getService().expelAllStudents(groupId))).build();
    }

    @GET
    @Path(value = "/isu/statistics/count-expelled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countExpelledStudents() {
        return Response.ok().entity(new CountExpelledDTO(getService().countExpelledStudents())).build();
    }

    @OPTIONS
    @Path("{path : .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response options() {
        return Response.status(202)
                .build();
    }
}
