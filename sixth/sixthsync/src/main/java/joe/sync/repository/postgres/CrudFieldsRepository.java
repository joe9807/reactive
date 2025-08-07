package joe.sync.repository.postgres;

import joe.sync.entity.postgres.ReactiveFields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudFieldsRepository extends CrudRepository<ReactiveFields, Long> {
}
