package joe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondDto {
    private String field11;
    private String field22;
    private String field33;
    private String field44;

    boolean needCallback;
}
