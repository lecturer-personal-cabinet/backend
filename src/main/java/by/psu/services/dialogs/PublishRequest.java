package by.psu.services.dialogs;

import by.psu.services.dialogs.model.PublisherServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishRequest {
    private String userId;
    private PublisherServiceRequest data;
    private String eventType;
}
