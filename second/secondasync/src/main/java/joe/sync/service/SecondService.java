package joe.sync.service;

import joe.reactive.dto.ThirdDto;
import joe.model.SecondDto;
import joe.sync.mapper.SecondMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecondService {
    private final SecondMapper mapper;

    public ThirdDto process(SecondDto secondDto){
        log.info("ASYNC: dto: {}", secondDto);
        return mapper.map(secondDto);
    }
}
