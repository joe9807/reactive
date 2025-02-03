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
public class FifthDto {
    private String field11111;
    private String field22222;
    private String field33333;
    private String field44444;

    private String callbackUrl;
    private LocalDateTime start;
}
