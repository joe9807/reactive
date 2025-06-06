package joe.reactive.sixth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SixthDto implements Serializable {
    private String field111111;
    private String field222222;
    private String field333333;
    private String field444444;
}
