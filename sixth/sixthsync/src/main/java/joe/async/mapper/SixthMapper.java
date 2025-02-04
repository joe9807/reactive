package joe.async.mapper;

import joe.reactive.sixth.SixthDto;
import joe.async.entity.ReactiveFields;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields map(SixthDto fifthDto, String type);
}
