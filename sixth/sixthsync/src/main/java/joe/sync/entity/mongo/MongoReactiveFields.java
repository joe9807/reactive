package joe.sync.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoReactiveFields {
    @Id
    private ObjectId id;
    private String type;
    private String field111111;
    private String field222222;
    private String field333333;
    private String field444444;

}
