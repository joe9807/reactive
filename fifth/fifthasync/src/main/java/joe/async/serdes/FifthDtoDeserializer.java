package joe.async.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import joe.model.FifthDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class FifthDtoDeserializer implements Deserializer<FifthDto> {
    @Override
    public FifthDto deserialize(String s, byte[] bytes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(bytes, FifthDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
