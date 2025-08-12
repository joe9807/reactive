package joe.sync.service;

import joe.reactive.sixth.SixthDto;
import joe.sync.mapper.SixthMapper;
import joe.sync.redis.RedisCache;
import joe.sync.repository.mongo.MongoFieldsRepository;
import joe.sync.repository.postgres.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SixthSyncService {
    private final MongoFieldsRepository mongoFieldsRepository;
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;
    private final RedisCache redisCache;

    public SixthDto findById(Long id){
        return redisCache.findById(id);
    }

    public SixthDto findByIdMongo(String id){
        return mapper.mapToDto(mongoFieldsRepository.findById(id).orElse(null));
    }

    public SixthDto process(SixthDto sixthDto){
        return redisCache.save(repository.save(mapper.mapToEntity(sixthDto, "SYNC")));
    }

    public SixthDto processMongo(SixthDto sixthDto){
        return mapper.mapToDto(mongoFieldsRepository.save(mapper.mapToMongoEntity(sixthDto, "SYNC")));
    }
}
