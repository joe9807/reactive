package joe.redis.repository;

import joe.redis.entity.ReactiveFields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudFieldsRepository extends CrudRepository<ReactiveFields, Long> {
}
