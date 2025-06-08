package joe.sync.service;

import joe.reactive.sixth.SixthDto;
import joe.sync.mapper.SixthMapper;
import joe.sync.redis.RedisCache;
import joe.sync.repository.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SixthSyncService {
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;
    private final RedisCache redisCache;

    public SixthDto findById(Long id){
        return redisCache.findById(id);
    }

    public SixthDto process(SixthDto sixthDto){
        return redisCache.save(repository.save(mapper.mapToEntity(sixthDto, "SYNC")));
    }
}
