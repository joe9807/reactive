package joe.reactive.fourth.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import joe.reactive.fifth.FifthDto;
import org.apache.kafka.common.serialization.Serializer;

public class FifthDtoSerializer implements Serializer<FifthDto> {

    @Override
    public byte[] serialize(String s, FifthDto fifthDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsBytes(fifthDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
