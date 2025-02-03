package joe.async.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import joe.model.FourthDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class FourthDtoDeserializer implements Deserializer<FourthDto> {
    @Override
    public FourthDto deserialize(String s, byte[] bytes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(bytes, FourthDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
