package joe.sync.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.model.FirstDto;
import joe.sync.config.AppConfig;
import joe.sync.mapper.FirstMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("sync/first")
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.model.mapper")
public class FirstSyncController {
    private final FirstMapper mapper;
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    @PostMapping("process")
    public JsonNode process(@RequestBody FirstDto firstDto){
        log.debug("SYNC: dto: {}", firstDto);
        return restTemplate.postForObject(URI.create(appConfig.getNext().getUrl()), mapper.map(firstDto), JsonNode.class);
    }
}
