package joe.reactive.first.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.first.client.FirstClient;
import joe.reactive.first.dto.FirstDto;
import joe.reactive.first.service.FirstService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("first")
@RequiredArgsConstructor
public class FirstController {
    private final FirstService firstService;
    private final FirstClient firstClient;

    @PostMapping("process")
    public Mono<JsonNode> process(@RequestBody Mono<FirstDto> firstDto){
        return firstDto.map(firstService::process).flatMap(firstClient::next);
    }
}
