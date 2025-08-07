package joe.sync.repository.mongo;

import joe.sync.entity.mongo.MongoReactiveFields;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoFieldsRepository extends MongoRepository<MongoReactiveFields, Long> {
}
