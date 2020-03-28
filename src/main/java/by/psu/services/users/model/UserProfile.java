package by.psu.services.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private String id;
    private String description;
    private String timezone;
    private String address;
    private String phoneNumber;
}
