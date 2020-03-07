package by.psu.services.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private UserType type;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String groupId;
}
