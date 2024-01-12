package org.example.api.controllers;

import org.example.api.utils.JndiUtils;
import org.example.ejb.service.IsuGroupService;
import org.example.isuapp.CountExpelledStudentsResponse;
import org.example.isuapp.ExpelStudentsRequest;
import org.example.isuapp.ExpelStudentsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class IsuEndpoint {

    private final String NAMESPACE_URI = "http://org/example/isuapp";

    private IsuGroupService getService() {
        return JndiUtils.getFromContext(IsuGroupService.class,
                "ejb:/ejb-1.0-SNAPSHOT-jar-with-dependencies/IsuGroupServiceImpl!org.example.ejb.service.IsuGroupService");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "expelStudentsRequest")
    @ResponsePayload
    public ExpelStudentsResponse expelStudents(@RequestPayload ExpelStudentsRequest expelStudentsRequest) {
        ExpelStudentsResponse expelStudentsResponse = new ExpelStudentsResponse();
        expelStudentsResponse.setExpelledStudentCount(getService().expelAllStudents(expelStudentsRequest.getGroupId()));
        return expelStudentsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "countExpelledStudentsRequest")
    @ResponsePayload
    public CountExpelledStudentsResponse countExpelledStudents() {
        System.out.println("(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
        CountExpelledStudentsResponse countExpelledStudents = new CountExpelledStudentsResponse();
        countExpelledStudents.setExpelledStudentCount(getService().countExpelledStudents());
        return countExpelledStudents;
    }
}
