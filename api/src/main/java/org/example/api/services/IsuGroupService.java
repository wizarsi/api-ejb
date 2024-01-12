package org.example.api.services;


public interface IsuGroupService {
    Long countExpelledStudents();

    Long expelAllStudents(Long groupId);
}
