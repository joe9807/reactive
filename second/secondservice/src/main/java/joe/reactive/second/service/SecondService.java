package joe.reactive.second.service;

import joe.reactive.dto.ThirdDto;
import joe.reactive.second.mapper.SecondMapper;
import joe.reactive.second.dto.SecondDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecondService {
    private final SecondMapper mapper;

    public ThirdDto process(SecondDto secondDto){
        log.info("process: {}", secondDto);
        return mapper.map(secondDto);
    }
}
