package by.psu.services.dialogs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dialog {
    private String id;
    private String name;
    private List<DialogMessage> messages;
    private List<DialogParticipant> participants;
    private String participantsHash;
}
