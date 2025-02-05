package joe.sync.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import joe.model.FourthDto;
import org.apache.kafka.common.serialization.Serializer;

public class ThirdDtoSerializer implements Serializer<FourthDto> {

    @Override
    public byte[] serialize(String s, FourthDto fourthDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsBytes(fourthDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
