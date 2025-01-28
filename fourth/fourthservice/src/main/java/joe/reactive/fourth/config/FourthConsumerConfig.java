package joe.reactive.fourth.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.kafka.KafkaClientMetrics;
import joe.reactive.fourth.FourthDto;
import joe.reactive.fourth.serdes.FourthDtoDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class FourthConsumerConfig {
    private final AppConfig appConfig;

    @Bean
    public ReceiverOptions<String, FourthDto> receiverOptions() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.getBootstrapServers());
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, appConfig.getGroupId());
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, FourthDtoDeserializer.class);

        return ReceiverOptions.<String, FourthDto>create(consumerProps)
                        .subscription(Collections.singleton(appConfig.getTopic()));
    }

    @Bean
    public KafkaReceiver<String, FourthDto> kafkaReceiver(ReceiverOptions<String, FourthDto> receiverOptions) {
        return KafkaReceiver.create(receiverOptions);
    }
}
