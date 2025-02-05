package joe.sync.repository;

import joe.sync.entity.ReactiveFields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudFieldsRepository extends CrudRepository<ReactiveFields, Long> {
}
