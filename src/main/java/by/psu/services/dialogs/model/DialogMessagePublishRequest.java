package by.psu.services.dialogs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DialogMessagePublishRequest extends PublisherServiceRequest {
    private String receiverId;
    private String senderId;
    private String messageId;
    private String dialogId;
}
