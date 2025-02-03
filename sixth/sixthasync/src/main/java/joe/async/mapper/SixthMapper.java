package joe.async.mapper;

import joe.async.entity.ReactiveFields;
import joe.reactive.sixth.SixthDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields map(SixthDto fifthDto, String type);
}
