package redis.service;

import joe.reactive.sixth.SixthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisMessagePublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    public void publish(SixthDto sixthDto) {
        redisTemplate.convertAndSend(topic.getTopic(), sixthDto);
    }
}