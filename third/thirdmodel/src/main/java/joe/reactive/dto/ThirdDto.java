package joe.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdDto {
    private String field111;
    private String field222;
    private String field333;
    private String field444;

    boolean needCallback;
}
