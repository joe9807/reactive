package joe.redis.service;

import joe.reactive.sixth.SixthDto;
import joe.redis.config.AppConfig;
import joe.redis.mapper.SixthMapper;
import joe.redis.repository.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber {
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;
    private final RedissonClient redissonClient;
    private final AppConfig appConfig;

    public void handleMessage(SixthDto sixthDto) {
        log.info("Message received {}", sixthDto);
        RLock rlock = redissonClient.getLock(appConfig.getRedissonLockName());

        try {
            if (rlock.tryLock(10, TimeUnit.SECONDS)) {
                repository.save(mapper.mapToEntity(sixthDto, "REDIS"));
            }
        } catch (Exception e) {
            log.error("Error during lock acquiring", e);
        } finally {
            if (rlock.isLocked() && rlock.isHeldByCurrentThread()){
                rlock.unlock();
            }
        }
    }
}