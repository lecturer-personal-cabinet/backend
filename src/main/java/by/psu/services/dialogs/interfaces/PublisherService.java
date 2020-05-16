package by.psu.services.dialogs.interfaces;

import by.psu.services.dialogs.model.DialogMessagePublishRequest;
import org.springframework.stereotype.Component;

@Component
public interface PublisherService {
    DialogMessagePublishRequest publishMessageRequest(String userId, DialogMessagePublishRequest request);
}
