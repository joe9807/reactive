package joe.sync.controller;

import io.micrometer.core.instrument.MeterRegistry;
import joe.model.FourthDto;
import joe.reactive.dto.ThirdCallbackDto;
import joe.reactive.dto.ThirdDto;
import joe.sync.kafka.ThirdProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("async/third")
@RequiredArgsConstructor
public class ThirdController {
    private final ThirdProducer thirdProducer;
    private final KafkaSender<String, FourthDto> kafkaSender;
    private final MeterRegistry meterRegistry;

    @PostMapping("process")
    public Flux<Map<String, String>> process(@RequestBody Mono<ThirdDto> thirdDtoMono){
        return kafkaSender.send(thirdDtoMono.map(thirdProducer::createRecord))
                .doOnNext(unused -> meterRegistry.counter("kafka.fourth.records.produced").increment())
                .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                .onErrorComplete()
                .map(stringSenderResult -> Map.of("correlationMetadata", stringSenderResult.correlationMetadata()))
                .doOnNext(result -> log.info("result: {}", result));
    }

    @PostMapping("callback")
    public Mono<Void> callback(@RequestBody Mono<ThirdCallbackDto> callbackDtoMono){
        return callbackDtoMono.flatMap(thirdCallbackDto -> {
            log.info("Message with key {} was process in {} ms", thirdCallbackDto.getKey(), thirdCallbackDto.getTimeElapsed());
            return Mono.empty();
        });
    }
}
