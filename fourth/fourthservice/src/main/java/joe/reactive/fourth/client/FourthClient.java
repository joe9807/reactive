package joe.reactive.fourth.client;

import io.micrometer.core.instrument.MeterRegistry;
import joe.reactive.dto.ThirdCallbackDto;
import joe.reactive.fourth.FourthDto;
import joe.reactive.fourth.kafka.FourthProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.SenderResult;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class FourthClient {
    private final FourthProducer fourthProducer;
    private final MeterRegistry meterRegistry;
    private final WebClient webClient;

    public Flux<Object> process(ConsumerRecord<String, FourthDto> record){
        log.info("process: {}; {}", record.key(), record.value());

        Flux<SenderResult<String>> fluxNext = fourthProducer.send(record.value())
                .doOnNext(unused -> meterRegistry.counter("kafka.fourth.records.produced").increment())
                .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                .onErrorComplete();

        if (record.value().getCallbackUrl() != null) {
            long timeElapsed = ChronoUnit.MILLIS.between(record.value().getStart(), LocalDateTime.now());

            return Flux.merge(webClient.post().uri(record.value().getCallbackUrl())
                            .bodyValue(ThirdCallbackDto.builder().timeElapsed(timeElapsed).key(record.key()).build())
                            .retrieve().bodyToFlux(String.class)
                            .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                            .onErrorComplete(),
                    fluxNext);
        } else {
            return fluxNext.map(stringSenderResult -> stringSenderResult);
        }
    }
}
