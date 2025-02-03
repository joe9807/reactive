package joe.async.repository;

import joe.async.entity.ReactiveFields;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveFieldsRepository extends R2dbcRepository<ReactiveFields, Long> {
}
