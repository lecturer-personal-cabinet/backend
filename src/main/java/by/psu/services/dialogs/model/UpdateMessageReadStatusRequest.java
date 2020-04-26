package by.psu.services.dialogs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageReadStatusRequest {
    private Boolean status;
    private List<String> senders;
}
