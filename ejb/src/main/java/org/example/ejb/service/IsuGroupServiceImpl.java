package org.example.ejb.service;


import org.example.ejb.exceptions.ServerNotAvailable;
import org.example.ejb.exceptions.UnrealMeaningException;
import org.example.ejb.model.ErrorDTO;
import org.example.ejb.model.StudyGroupBase;
import org.jboss.ejb3.annotation.Pool;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.Optional;

import static org.example.ejb.service.util.Routes.URL_GET_ALL_GROUP;
import static org.example.ejb.service.util.Routes.URL_GET_GROUP;

@Stateless
@Pool(value = "isuGroupServicePool")
public class IsuGroupServiceImpl implements IsuGroupService {

    @EJB
    private NetworkService networkService;

    private boolean checkValidCountExpelDTO(Long countExpelled) {
        Optional.ofNullable(countExpelled)
                .filter(count -> count >= 0)
                .orElseThrow(() -> new UnrealMeaningException(String.valueOf(countExpelled)));
        return true;

    }

    public String ping() {
        return "pong";
    }

    public Long expelAllStudents(Long groupId) throws ServerNotAvailable {
        System.out.println("1 expelAllStudents: " + groupId);
        try {
            StudyGroupBase group = networkService.getTarget().path(URL_GET_GROUP)
                    .resolveTemplate("id", groupId).request()
                    .get().readEntity(new GenericType<StudyGroupBase>() {
                    });
            System.out.println("2 expelAllStudents: " + group.getStudentsCount());

            int fuck = Math.toIntExact(group.getStudentsCount() + group.getShouldBeExpelled() - 1);
            if (fuck <= 0) {
                fuck = 1;
            }
            group.setShouldBeExpelled(fuck);
            group.setStudentsCount(1L);
            return group.getStudentsCount();
        } catch (Exception e) {
            System.out.println("Exception!!! expelAllStudents");
            e.printStackTrace();
            throw new ServerNotAvailable(new ErrorDTO("Сервер недоступен"));
        }
    }

    public Long countExpelledStudents() throws ServerNotAvailable {
        //get groups info
        System.out.println("1 countExpelledStudents");
        try {
            List<StudyGroupBase> allGroup = networkService.getTarget().path(URL_GET_ALL_GROUP).request()
                    .get().readEntity(new GenericType<List<StudyGroupBase>>() {
                    });
            allGroup.forEach(System.out::println);
            System.out.println("2 countExpelledStudents: " + (long) allGroup.size());
            Long countExpellStudent = allGroup.stream().map(StudyGroupBase::getShouldBeExpelled).reduce(0, Integer::sum).longValue();
            checkValidCountExpelDTO(countExpellStudent);
            return countExpellStudent;
        } catch (Exception e) {
            System.out.println("Exception!!! countExpelledStudents");
            e.printStackTrace();
            throw new ServerNotAvailable(new ErrorDTO("Сервер недоступен"));
        }

    }

}