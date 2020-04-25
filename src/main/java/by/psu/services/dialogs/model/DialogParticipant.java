package by.psu.services.dialogs.model;

import by.psu.services.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DialogParticipant {
    private String id;
    private User user;
}
