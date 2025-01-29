package joe.reactive.fourth.controller;

import joe.reactive.fourth.FourthCallbackDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("async/fourth")
@RequiredArgsConstructor
public class FourthController {

    @PostMapping("callback")
    public Mono<Void> callback(@RequestBody Mono<FourthCallbackDto> callbackDtoMono){
        return callbackDtoMono.flatMap(callbackDto -> {
            log.info("Message with key {} was process in {} ms", callbackDto.getKey(), callbackDto.getTimeElapsed());
            return Mono.empty();
        });
    }
}
