package org.example.api.integration;

import org.example.api.model.dto.rest.StudyGroupBase;

import java.util.List;

public interface StudyGroupRestTemplate {
    StudyGroupBase getGroupById(Long id);

    List<StudyGroupBase> getAllStudyGroups();
}
