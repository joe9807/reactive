package joe.reactive.third.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.dto.ThirdDto;
import joe.reactive.third.config.AppConfig;
import joe.reactive.third.mapper.ThirdMapper;
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
@RequestMapping("sync/third")
@RequiredArgsConstructor
public class ThirdSyncController {
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;
    private final ThirdMapper mapper;

    @PostMapping("process")
    public JsonNode process(@RequestBody ThirdDto thirdDto){
        return restTemplate.postForObject(URI.create(appConfig.getNext().getSyncUrl()), mapper.map(thirdDto, null), JsonNode.class);
    }
}
