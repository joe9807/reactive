package joe.sync.mapper;

import joe.reactive.sixth.SixthDto;
import joe.sync.entity.ReactiveFields;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields mapToEntity(SixthDto fifthDto, String type);
    SixthDto mapToDto(ReactiveFields reactiveFields);
}
