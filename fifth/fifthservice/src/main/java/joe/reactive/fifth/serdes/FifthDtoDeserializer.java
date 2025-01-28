package joe.reactive.fifth.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import joe.reactive.fifth.FifthDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class FifthDtoDeserializer implements Deserializer {
    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(bytes, FifthDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
