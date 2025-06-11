package joe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstDto {
    private String field1;
    private String field2;
    private String field3;
    private String field4;

    boolean needCallback;

    public static FirstDto create(boolean needCallback){
        return FirstDto.builder()
                .field1(UUID.randomUUID().toString())
                .field2(UUID.randomUUID().toString())
                .field3(UUID.randomUUID().toString())
                .field4(UUID.randomUUID().toString())
                .needCallback(needCallback)
                .build();
    }
}
