package joe.async.config;

import joe.async.serdes.FifthDtoDeserializer;
import joe.model.FifthDto;
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
public class FifthConsumerConfig {
    private final AppConfig appConfig;

    @Bean
    public ReceiverOptions<String, FifthDto> receiverOptions() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.getBootstrapServers());
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, appConfig.getGroupId());
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, FifthDtoDeserializer.class);

        return ReceiverOptions.<String, FifthDto>create(consumerProps)
                        .subscription(Collections.singleton(appConfig.getTopic()));
    }

    @Bean
    public KafkaReceiver<String, FifthDto> kafkaReceiver(ReceiverOptions<String, FifthDto> receiverOptions) {
        return KafkaReceiver.create(receiverOptions);
    }
}
