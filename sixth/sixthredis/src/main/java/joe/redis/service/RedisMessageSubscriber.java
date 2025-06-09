package joe.redis.service;

import joe.reactive.sixth.SixthDto;
import joe.redis.mapper.SixthMapper;
import joe.redis.repository.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber {
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;

    public void handleMessage(SixthDto sixthDto) {
        log.info("Message received {}", sixthDto);
        repository.save(mapper.mapToEntity(sixthDto, "REDIS"));
    }
}