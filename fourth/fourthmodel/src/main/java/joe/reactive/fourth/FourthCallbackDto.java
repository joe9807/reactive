package joe.reactive.fourth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FourthCallbackDto {
    private Long timeElapsed;
    private String key;
}
