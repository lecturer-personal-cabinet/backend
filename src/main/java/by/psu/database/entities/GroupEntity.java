package by.psu.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "group")
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "group")
    private Set<ClassroomCodeAssignmentEntity> classroomCodeAssignments;
}
