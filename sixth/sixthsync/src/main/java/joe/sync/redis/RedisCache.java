package joe.sync.redis;

import joe.reactive.sixth.SixthDto;
import joe.sync.entity.postgres.ReactiveFields;
import joe.sync.mapper.SixthMapper;
import joe.sync.repository.postgres.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisCache {
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;

    @Cacheable(key = "#id", value = "fields")
    public SixthDto findById(Long id){
        return mapper.mapToDto(repository.findById(id).orElse(null));
    }

    @Cacheable(key = "#reactiveFields.id", value = "fields")
    public SixthDto save(ReactiveFields reactiveFields){
        return mapper.mapToDto(reactiveFields);
    }
}
