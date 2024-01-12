package org.example.api.services;


import lombok.RequiredArgsConstructor;
import org.example.api.exceptions.ServerNotAvailable;
import org.example.api.exceptions.UnrealMeaningException;
import org.example.api.integration.StudyGroupRestTemplate;
import org.example.api.model.dto.rest.ErrorDTO;
import org.example.api.model.dto.rest.StudyGroupBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class IsuGroupServiceImpl implements IsuGroupService {


    private final StudyGroupRestTemplate studyGroupRestTemplate;

    private void checkValidCountExpelDTO(Long countExpelled) {
        Optional.ofNullable(countExpelled)
                .filter(count -> count >= 0)
                .orElseThrow(() -> new UnrealMeaningException(String.valueOf(countExpelled)));
    }

    public String ping() {
        return "pong";
    }

    public Long expelAllStudents(Long groupId) throws ServerNotAvailable {
        try {
            StudyGroupBase group = studyGroupRestTemplate.getGroupById(groupId);

            int fuck = Math.toIntExact(group.getStudentsCount() + group.getShouldBeExpelled() - 1);
            if (fuck <= 0) {
                fuck = 1;
            }
            group.setShouldBeExpelled(fuck);
            group.setStudentsCount(1L);
            return group.getStudentsCount();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerNotAvailable(new ErrorDTO("Сервер недоступен"));
        }
    }

    public Long countExpelledStudents() throws ServerNotAvailable {
        try {
            List<StudyGroupBase> allGroup = studyGroupRestTemplate.getAllStudyGroups();
            Long countExpellStudent = allGroup.stream().map(StudyGroupBase::getShouldBeExpelled).reduce(0, Integer::sum).longValue();
            checkValidCountExpelDTO(countExpellStudent);
            return countExpellStudent;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerNotAvailable(new ErrorDTO("Сервер недоступен"));
        }

    }

}