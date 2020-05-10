package by.psu.services.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Null
    private String patronymic;

    @NotNull
    private String email;

    @Null
    private String groupId;
    
    private String image;
}
