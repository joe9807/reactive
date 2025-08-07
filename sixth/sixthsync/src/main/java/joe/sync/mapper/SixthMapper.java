package joe.sync.mapper;

import joe.reactive.sixth.SixthDto;
import joe.sync.entity.mongo.MongoReactiveFields;
import joe.sync.entity.postgres.ReactiveFields;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    MongoReactiveFields mapToMongoEntity(SixthDto fifthDto, String type);
    ReactiveFields mapToEntity(SixthDto fifthDto, String type);
    SixthDto mapToDto(ReactiveFields reactiveFields);
}
