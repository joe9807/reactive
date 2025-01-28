package joe.reactive.second.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecondDto {
    private String field11;
    private String field22;
    private String field33;
    private String field44;

    boolean needCallback;
}
