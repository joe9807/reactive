package redis.service;

import joe.model.FifthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.mapper.FifthMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber {
    private final RedisMessagePublisher redisMessagePublisher;
    private final FifthMapper mapper;

    public void handleMessage(FifthDto fifthDto) {
        log.info("Message received {}", fifthDto);
        redisMessagePublisher.publish(mapper.map(fifthDto));
    }
}