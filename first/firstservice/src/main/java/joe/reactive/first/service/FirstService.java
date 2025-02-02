package joe.reactive.first.service;

import joe.reactive.first.dto.FirstDto;
import joe.reactive.first.mapper.FirstMapper;
import joe.reactive.second.dto.SecondDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirstService {
    private final FirstMapper mapper;

    public SecondDto process(FirstDto firstDto){
        log.info("ASYNC: dto: {}", firstDto);
        return mapper.map(firstDto);
    }
}
