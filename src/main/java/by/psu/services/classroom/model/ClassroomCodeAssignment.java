package by.psu.services.classroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomCodeAssignment {
    private String id;

    @NotNull
    private String classroomCodeId;

    @NotNull
    private String groupId;
}
