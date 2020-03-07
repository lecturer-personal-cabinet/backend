package by.psu.database.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "google_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUserEntity {
    @Id
    @Getter
    @Setter
    @Column(name = "id")
    private String id;

    @Getter
    @Setter
    @Column(name = "full_name")
    private String fullName;

    @Getter
    @Setter
    @Column(name = "given_name")
    private String givenName;

    @Getter
    @Setter
    @Column(name = "family_name")
    private String familyName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    @Getter
    @Setter
    @Column(name = "token")
    private String token;
}

