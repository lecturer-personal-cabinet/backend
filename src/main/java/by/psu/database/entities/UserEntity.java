package by.psu.database.entities;

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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
    private String type;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @OneToMany(mappedBy="sender", fetch = FetchType.LAZY)
    private Set<DialogMessageEntity> messages;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<ClassroomCodeEntity> classroomCodes;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private Set<UserTimelinePostEntity> userTimelinePosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<PortfolioCardEntity> portfolioCards;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfileEntity profileEntity;
}
