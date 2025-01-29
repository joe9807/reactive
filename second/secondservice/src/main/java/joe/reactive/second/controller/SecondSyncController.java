package joe.reactive.second.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.second.config.AppConfig;
import joe.reactive.second.dto.SecondDto;
import joe.reactive.second.service.SecondService;
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
    private final SecondService secondService;
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    @PostMapping("process")
    public JsonNode process(@RequestBody SecondDto firstDto){
        return restTemplate.postForObject(URI.create(appConfig.getNext().getSyncUrl()), secondService.process(firstDto), JsonNode.class);
    }
}
