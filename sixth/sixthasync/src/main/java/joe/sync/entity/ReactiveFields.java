package joe.sync.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactiveFields {
    @Id
    private Long id;
    private String type;
    private String field111111;
    private String field222222;
    private String field333333;
    private String field444444;
}
