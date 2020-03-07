package by.psu.services.classroom.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomCode {
    private String id;

    @NotNull
    @Length(max = 100, min = 10)
    private String title;

    @NotNull
    @Length(max = 8, min = 1)
    private String classroomCode;

    @NotNull
    private String ownerId;
}
