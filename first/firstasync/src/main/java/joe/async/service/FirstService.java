package joe.async.service;

import joe.model.FirstDto;
import joe.model.mapper.FirstMapper;
import joe.reactive.second.dto.SecondDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.model.mapper")
public class FirstService {
    private final FirstMapper mapper;

    public SecondDto process(FirstDto firstDto){
        log.info("ASYNC: dto: {}", firstDto);
        return mapper.map(firstDto);
    }
}
