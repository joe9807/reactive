package joe.sync.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.sync.config.AppConfig;
import joe.model.FifthDto;
import joe.sync.mapper.FifthMapper;
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
@RequestMapping("sync/fifth")
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.model.mapper")
public class FifthSyncController {
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;
    private final FifthMapper mapper;

    @PostMapping("process")
    public JsonNode process(@RequestBody FifthDto fifthDto){
        log.debug("SYNC: dto: {}", fifthDto);
        return restTemplate.postForObject(URI.create(appConfig.getNext().getUrl()), mapper.map(fifthDto), JsonNode.class);
    }
}
