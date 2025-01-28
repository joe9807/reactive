package joe.reactive.second.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.second.client.SecondClient;
import joe.reactive.second.dto.SecondDto;
import joe.reactive.second.service.SecondService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("second")
@RequiredArgsConstructor
public class SecondController {
    private final SecondService secondService;
    private final SecondClient secondClient;

    @PostMapping("process")
    public Mono<JsonNode> process(@RequestBody Mono<SecondDto> secondDto){
        return secondDto.map(secondService::process).flatMap(secondClient::next);
    }
}
