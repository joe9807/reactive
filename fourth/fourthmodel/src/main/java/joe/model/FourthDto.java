package joe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FourthDto {
    private String field1111;
    private String field2222;
    private String field3333;
    private String field4444;

    private String callbackUrl;
    private LocalDateTime start;
}
