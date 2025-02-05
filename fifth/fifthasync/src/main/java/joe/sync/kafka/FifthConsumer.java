package joe.sync.kafka;

import io.micrometer.core.instrument.MeterRegistry;
import joe.model.FifthDto;
import joe.sync.client.FifthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;

@Slf4j
@Component
@RequiredArgsConstructor
public class FifthConsumer {
    private final KafkaReceiver<String, FifthDto> kafkaReceiver;
    private final FifthClient fifthClient;
    private final MeterRegistry meterRegistry;

    @EventListener(ApplicationReadyEvent.class)
    public void listener() {
        kafkaReceiver.receive()
                .doOnNext(unused -> meterRegistry.counter("kafka.fourth.records.consumed").increment())
                .flatMap(fifthClient::process)
                .subscribe();
    }
}
