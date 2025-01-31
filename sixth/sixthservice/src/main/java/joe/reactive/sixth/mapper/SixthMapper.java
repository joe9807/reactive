package joe.reactive.sixth.mapper;

import joe.reactive.sixth.SixthDto;
import joe.reactive.sixth.entity.ReactiveFields;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields map(SixthDto fifthDto, String type);
}
