package by.psu.services.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private String id;
    private String description;
    private TimeZone timezone;
    private String address;
    private String phoneNumber;
}
