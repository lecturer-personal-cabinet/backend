package by.psu.services.classroom.interfaces;

import by.psu.services.classroom.model.ClassroomCode;
import by.psu.services.classroom.model.ClassroomCodeAssignment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClassroomService {
    List<ClassroomCode> getAllCodes(String ownerId);
    ClassroomCode saveCode(ClassroomCode classroomCode);
    void deleteCode(String id);

    ClassroomCodeAssignment assignCode(ClassroomCodeAssignment classroomCodeAssignment);
    void deleteAssignment(String id);
    List<ClassroomCode> getAllForGroup(String groupId);
}
