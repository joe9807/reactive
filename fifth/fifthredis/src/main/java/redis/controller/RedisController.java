package redis.controller;

import joe.model.FifthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.mapper.FifthMapper;
import redis.service.RedisMessagePublisher;

@RestController
@RequestMapping("redis")
@RequiredArgsConstructor
public class RedisController {
    private final RedisMessagePublisher redisMessagePublisher;
    private final FifthMapper mapper;

    @PostMapping("send")
    public void send(@RequestBody FifthDto fifthDto){
        redisMessagePublisher.publish(mapper.map(fifthDto));
    }
}
