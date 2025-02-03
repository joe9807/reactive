package joe.async.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.async.client.FirstClient;
import joe.model.FirstDto;
import joe.async.service.FirstService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("async/first")
@RequiredArgsConstructor
public class FirstAsyncController {
    private final FirstService firstService;
    private final FirstClient firstClient;

    @PostMapping("process")
    public Mono<JsonNode> process(@RequestBody Mono<FirstDto> firstDto){
        return firstDto.map(firstService::process).flatMap(firstClient::next);
    }
}
