package by.psu.database.entities;

import by.psu.services.users.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @Column(name = "type", columnDefinition = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @OneToMany(mappedBy = "owner")
    private Set<ClassroomCodeEntity> classroomCodes;

    @OneToMany(mappedBy = "sender")
    private Set<UserTimelinePostEntity> userTimelinePosts;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserProfileEntity profileEntity;
}
