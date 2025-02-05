package joe.sync.controller;

import joe.reactive.sixth.SixthDto;
import joe.sync.entity.ReactiveFields;
import joe.sync.service.SixthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("async/sixth")
@RequiredArgsConstructor
public class SixthController {
    private final SixthService sixthService;

    @PostMapping("process")
    public Mono<ReactiveFields> process(@RequestBody Mono<SixthDto> sixthDtoMono){
        return sixthDtoMono.flatMap(sixthService::process);
    }
}
