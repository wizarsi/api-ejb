package org.example.api.controllers;


import lombok.RequiredArgsConstructor;
import org.example.api.services.IsuGroupService;
import org.example.isuapp.CountExpelledStudentsResponse;
import org.example.isuapp.ExpelStudentsRequest;
import org.example.isuapp.ExpelStudentsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class IsuEndpoint {

    private final String NAMESPACE_URI = "http://org/example/isuapp";

    private final IsuGroupService isuGroupService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "expelStudentsRequest")
    @ResponsePayload
    public ExpelStudentsResponse expelStudents(@RequestPayload ExpelStudentsRequest expelStudentsRequest) {
        ExpelStudentsResponse expelStudentsResponse = new ExpelStudentsResponse();
        expelStudentsResponse.setExpelledStudentCount(isuGroupService.expelAllStudents(expelStudentsRequest.getGroupId()));
        return expelStudentsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "countExpelledStudentsRequest")
    @ResponsePayload
    public CountExpelledStudentsResponse countExpelledStudents() {
        CountExpelledStudentsResponse countExpelledStudents = new CountExpelledStudentsResponse();
        countExpelledStudents.setExpelledStudentCount(isuGroupService.countExpelledStudents());
        return countExpelledStudents;
    }
}
