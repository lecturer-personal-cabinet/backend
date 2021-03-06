package by.psu.database.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "entity_key")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "description")
    private String description;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
}
