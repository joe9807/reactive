package joe.reactive.first.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.first.config.AppConfig;
import joe.reactive.first.dto.FirstDto;
import joe.reactive.first.service.FirstService;
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
@RequestMapping("sync/first")
@RequiredArgsConstructor
public class FirstSyncController {
    private final FirstService firstService;
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    @PostMapping("process")
    public JsonNode process(@RequestBody FirstDto firstDto){
        return restTemplate.postForObject(URI.create(appConfig.getNext().getSyncUrl()), firstService.process(firstDto), JsonNode.class);
    }
}
