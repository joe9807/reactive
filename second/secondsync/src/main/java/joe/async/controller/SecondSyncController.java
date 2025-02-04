package joe.async.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.model.SecondDto;
import joe.async.config.AppConfig;
import joe.async.mapper.SecondMapper;
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
@RequestMapping("sync/second")
@RequiredArgsConstructor
public class SecondSyncController {
    private final SecondMapper mapper;
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    @PostMapping("process")
    public JsonNode process(@RequestBody SecondDto secondDto){
        log.info("SYNC: dto: {}", secondDto);
        return restTemplate.postForObject(URI.create(appConfig.getNext().getUrl()), mapper.map(secondDto), JsonNode.class);
    }
}
