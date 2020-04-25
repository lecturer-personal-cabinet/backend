package by.psu.services.dialogs.model;

import by.psu.services.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DialogMessage {
    private String id;
    private String dialogId;
    private String content;
    private LocalDateTime createdTs;
    private User sender;
}
