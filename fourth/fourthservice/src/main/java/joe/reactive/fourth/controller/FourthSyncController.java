package joe.reactive.fourth.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.fourth.FourthDto;
import joe.reactive.fourth.config.AppConfig;
import joe.reactive.fourth.mapper.FourthMapper;
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
@RequestMapping("sync/fourth")
@RequiredArgsConstructor
public class FourthSyncController {
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;
    private final FourthMapper mapper;

    @PostMapping("process")
    public JsonNode process(@RequestBody FourthDto fourthDto){
        return restTemplate.postForObject(URI.create(appConfig.getNext().getSyncUrl()), mapper.map(fourthDto, null), JsonNode.class);
    }
}
