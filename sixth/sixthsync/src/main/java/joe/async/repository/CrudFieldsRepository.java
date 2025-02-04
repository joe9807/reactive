package joe.async.repository;

import joe.async.entity.ReactiveFields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudFieldsRepository extends CrudRepository<ReactiveFields, Long> {
}
