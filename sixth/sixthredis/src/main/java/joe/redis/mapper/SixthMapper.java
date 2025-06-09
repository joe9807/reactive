package joe.redis.mapper;

import joe.reactive.sixth.SixthDto;
import joe.redis.entity.ReactiveFields;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields mapToEntity(SixthDto fifthDto, String type);
}
