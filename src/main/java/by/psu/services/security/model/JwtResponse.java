package by.psu.services.security.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    @Getter
    @Setter
    private String jwttoken;
}
