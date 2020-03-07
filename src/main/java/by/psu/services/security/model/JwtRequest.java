package by.psu.services.security.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
