package by.psu.services.dialogs.implementations;

import by.psu.services.dialogs.PublishRequest;
import by.psu.services.dialogs.interfaces.PublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public PublisherServiceImpl(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public PublishRequest publishMessageRequest(String userId, PublishRequest request) {
        System.out.println("Send request: " + request);
        try {
            String value = objectMapper.writeValueAsString(request);
            this.stringRedisTemplate.convertAndSend(userId, value);
            return request;
        } catch(Exception e) {
            System.out.println("Exception: " + e);
            throw new IllegalArgumentException(e);
        }
    }
}
