package joe.async.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.async.client.SecondClient;
import joe.model.SecondDto;
import joe.async.service.SecondService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("async/second")
@RequiredArgsConstructor
public class SecondAsyncController {
    private final SecondService secondService;
    private final SecondClient secondClient;

    @PostMapping("process")
    public Mono<JsonNode> process(@RequestBody Mono<SecondDto> secondDto){
        return secondDto.map(secondService::process).flatMap(secondClient::next);
    }
}
