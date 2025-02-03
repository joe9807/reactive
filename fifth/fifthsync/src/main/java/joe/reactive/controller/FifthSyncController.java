package joe.reactive.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.config.AppConfig;
import joe.reactive.fifth.FifthDto;
import joe.reactive.fifth.mapper.FifthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("sync/fifth")
@RequiredArgsConstructor
public class FifthSyncController {
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;
    private final FifthMapper mapper;

    @PostMapping("process")
    public JsonNode process(@RequestBody FifthDto fifthDto){
        log.info("SYNC: dto: {}", fifthDto);
        return restTemplate.postForObject(URI.create(appConfig.getNext().getUrl()), mapper.map(fifthDto), JsonNode.class);
    }
}
