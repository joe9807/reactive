package joe.async.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
