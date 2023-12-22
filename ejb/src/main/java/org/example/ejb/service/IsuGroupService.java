package org.example.ejb.service;

import javax.ejb.Remote;

@Remote
public interface IsuGroupService {
    Long countExpelledStudents();

    Long expelAllStudents(Long groupId);

    String ping();

}
