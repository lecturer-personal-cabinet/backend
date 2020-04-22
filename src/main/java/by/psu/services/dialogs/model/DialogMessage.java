package by.psu.services.dialogs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogMessage {
    private String id;
    private String dialogId;
    private String content;
    private LocalDateTime createdTs;
}
