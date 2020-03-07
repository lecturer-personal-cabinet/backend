package by.psu.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "classroom_code_assignment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomCodeAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @ManyToOne
    @JoinColumn(name = "classroom_code_id")
    private ClassroomCodeEntity classroomCode;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
}
