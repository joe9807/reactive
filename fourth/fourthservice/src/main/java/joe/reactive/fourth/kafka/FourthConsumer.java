package joe.reactive.fourth.kafka;

import io.micrometer.core.instrument.MeterRegistry;
import joe.reactive.fourth.FourthDto;
import joe.reactive.fourth.client.FourthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;

@Slf4j
@Component
@RequiredArgsConstructor
public class FourthConsumer {
    private final KafkaReceiver<String, FourthDto> kafkaReceiver;
    private final FourthClient fourthClient;
    private final MeterRegistry meterRegistry;

    @EventListener(ApplicationReadyEvent.class)
    public void listener() {
        kafkaReceiver.receive()
                .flatMap(fourthClient::process)
                .doOnNext(unused -> meterRegistry.counter("kafka.fourth.records.consumed").increment())
                .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                .onErrorComplete()
                .subscribe();
    }
}
