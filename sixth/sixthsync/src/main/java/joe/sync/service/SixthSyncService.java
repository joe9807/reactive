package joe.sync.service;

import joe.reactive.sixth.SixthDto;
import joe.sync.mapper.SixthMapper;
import joe.sync.repository.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SixthSyncService {
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;

    @Cacheable(key = "#id", value = "fields")
    public SixthDto findById(Long id){
        return mapper.mapToDto(repository.findById(id).orElse(null));
    }

    public SixthDto process(SixthDto sixthDto){
        return mapper.mapToDto(repository.save(mapper.mapToEntity(sixthDto, "SYNC")));
    }
}
