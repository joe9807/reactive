package joe.sync.kafka;

import joe.model.FourthDto;
import joe.reactive.dto.ThirdDto;
import joe.sync.config.AppConfig;
import joe.sync.mapper.ThirdMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import reactor.kafka.sender.SenderRecord;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThirdProducer {
    private final AppConfig appConfig;
    private final ThirdMapper mapper;

    public SenderRecord<String, FourthDto, String> createRecord(ThirdDto thirdDto){
        log.debug("ASYNC: dto: {}", thirdDto);
        String key = UUID.randomUUID().toString();
        return SenderRecord.create(new ProducerRecord<>(appConfig.getNext().getTopic(), key, mapper.map(thirdDto, appConfig.getCallbackUrl())), key);
    }
}
