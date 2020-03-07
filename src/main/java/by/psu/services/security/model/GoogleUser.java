package by.psu.services.security.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUser {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private String givenName;

    @Getter
    @Setter
    private String familyName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String imageUrl;

    @Getter
    @Setter
    private String token;
}
