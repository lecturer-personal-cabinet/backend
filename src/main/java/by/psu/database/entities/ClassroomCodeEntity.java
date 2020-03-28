package by.psu.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "classroom_codes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "classroom_code")
    @NotNull
    private String classroomCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private UserEntity owner;

    @OneToMany(mappedBy = "classroomCode")
    private Set<ClassroomCodeAssignmentEntity> classroomCodeAssignments;
}
