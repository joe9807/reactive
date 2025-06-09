package redis.service;

import joe.reactive.sixth.SixthDto;

public interface MessagePublisher {
    void publish(SixthDto sixthDto);
}