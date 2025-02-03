package joe.reactive.fifth.client;

import joe.reactive.fifth.FifthDto;
import joe.reactive.fifth.mapper.FifthMapper;
import joe.reactive.fourth.FourthCallbackDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class FifthClient {
    private final FifthMapper mapper;
    private final WebClient webClient;

    public Flux<Object> process(ConsumerRecord<String, FifthDto> record){
        log.info("ASYNC: record: {}; {}", record.key(), record.value());

        Flux<Object> nextFlux = webClient.post()
                .bodyValue(mapper.map(record.value()))
                .retrieve().bodyToFlux(Object.class)
                .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                .onErrorComplete();

        if (record.value().getCallbackUrl() != null) {
            long timeElapsed = ChronoUnit.MILLIS.between(record.value().getStart(), LocalDateTime.now());

            return nextFlux.flatMap(o -> webClient.post().uri(record.value().getCallbackUrl())
                            .bodyValue(FourthCallbackDto.builder().timeElapsed(timeElapsed).key(record.key()).build())
                            .retrieve().bodyToFlux(String.class)
                            .doOnError(e-> log.error("Send callback failed {}", e.getMessage()))
                            .onErrorComplete());
        } else {
            return nextFlux;
        }
    }
}
