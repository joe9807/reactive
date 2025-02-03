package joe.reactive.fourth.kafka;

import joe.model.FifthDto;
import joe.reactive.fourth.FourthDto;
import joe.reactive.fourth.config.AppConfig;
import joe.reactive.fourth.mapper.FourthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FourthProducer {
    private final AppConfig appConfig;
    private final FourthMapper mapper;
    private final KafkaSender<String, FifthDto> kafkaSender;

    public Flux<SenderResult<String>> send(FourthDto fourthDto){
        return kafkaSender.send(Mono.just(createRecord(fourthDto)));
    }

    public SenderRecord<String, FifthDto, String> createRecord(FourthDto fourthDto){
        String key = UUID.randomUUID().toString();
        return SenderRecord.create(new ProducerRecord<>(appConfig.getNext().getTopic(), key, mapper.map(fourthDto, appConfig.getCallbackUrl())), key);
    }
}
