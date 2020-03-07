package by.psu.controllers;

import by.psu.services.classroom.interfaces.ClassroomService;
import by.psu.services.classroom.model.ClassroomCode;
import by.psu.services.classroom.model.ClassroomCodeAssignment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping(path = "/classroom/code/{ownerId}",
            produces = "application/json")
    public List<ClassroomCode> getAll(@PathVariable("ownerId") String ownerId) {
        return classroomService.getAllCodes(ownerId);
    }

    @PostMapping(path = "/classroom/code",
            produces = "application/json",
            consumes = "application/json")
    public ClassroomCode save(@RequestBody ClassroomCode classroomCode) {
        return classroomService.saveCode(classroomCode);
    }

    @DeleteMapping(path = "/classroom/code/{id}")
    public void delete(@PathVariable("id") String id) {
        classroomService.deleteCode(id);
    }

    @GetMapping("/classroom/assignment/code/{groupId}")
    public List<ClassroomCode> getAllForGroupId(@PathVariable("groupId") String groupId) {
        return classroomService.getAllForGroup(groupId);
    }

    @PostMapping(path = "/classroom/code/assignment")
    public ClassroomCodeAssignment assignCodeToGroup(@RequestBody ClassroomCodeAssignment classroomCodeAssignment) {
        return classroomService.assignCode(classroomCodeAssignment);
    }

    @DeleteMapping(path = "/classroom/code/assignment/{id}")
    public void deleteAssignment(@PathVariable("id")String id) {
        classroomService.deleteAssignment(id);
    }
}
