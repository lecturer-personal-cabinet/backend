package by.psu.services.dialogs.interfaces;

import by.psu.services.dialogs.PublishRequest;
import org.springframework.stereotype.Component;

@Component
public interface PublisherService {
    PublishRequest publishMessageRequest(String userId, PublishRequest request);
}
