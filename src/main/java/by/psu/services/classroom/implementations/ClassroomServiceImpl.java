package by.psu.services.classroom.implementations;

import by.psu.database.entities.ClassroomCodeAssignmentEntity;
import by.psu.database.entities.ClassroomCodeEntity;
import by.psu.database.repositories.ClassroomCodeAssignmentsRepository;
import by.psu.database.repositories.ClassroomCodesRepository;
import by.psu.services.classroom.interfaces.ClassroomService;
import by.psu.services.classroom.mappers.ClassroomMapper;
import by.psu.services.classroom.model.ClassroomCode;
import by.psu.services.classroom.model.ClassroomCodeAssignment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// TODO(vladimir) Enforce permissions
@Service
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomCodesRepository classroomCodesRepository;
    private final ClassroomCodeAssignmentsRepository classroomCodeAssignmentsRepository;
    private final ClassroomMapper classroomMapper;

    public ClassroomServiceImpl (ClassroomCodesRepository classroomCodesRepository,
                                 ClassroomMapper classroomMapper,
                                 ClassroomCodeAssignmentsRepository classroomCodeAssignmentsRepository) {
        this.classroomCodesRepository = classroomCodesRepository;
        this.classroomMapper = classroomMapper;
        this.classroomCodeAssignmentsRepository = classroomCodeAssignmentsRepository;
    }

    @Override
    public List<ClassroomCode> getAllCodes(String ownerId) {
        List<ClassroomCodeEntity> codes = classroomCodesRepository.findAllByOwnerId(ownerId);
        return codes.stream().map(classroomMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ClassroomCode saveCode(ClassroomCode classroomCode) {
        ClassroomCodeEntity entityToSave = classroomMapper.toEntity(classroomCode);
        ClassroomCodeEntity savedEntity = classroomCodesRepository.save(entityToSave);
        return classroomMapper.toDto(savedEntity);
    }

    @Override
    public void deleteCode(String id) {
        classroomCodesRepository.deleteById(id);
    }

    @Override
    public ClassroomCodeAssignment assignCode(ClassroomCodeAssignment classroomCodeAssignment) {
        ClassroomCodeAssignmentEntity entityToSave = classroomMapper.toEntity(classroomCodeAssignment);
        ClassroomCodeAssignmentEntity savedEntity = classroomCodeAssignmentsRepository.save(entityToSave);
        return classroomMapper.toDto(savedEntity);
    }

    @Override
    public void deleteAssignment(String id) {
        classroomCodeAssignmentsRepository.deleteById(id);
    }

    @Override
    public List<ClassroomCode> getAllForGroup(String groupId) {
        return classroomCodeAssignmentsRepository
                .getAllByGroup_Id(groupId)
                .stream()
                .map(ClassroomCodeAssignmentEntity::getClassroomCode)
                .map(classroomMapper::toDto)
                .collect(Collectors.toList());
    }


}
