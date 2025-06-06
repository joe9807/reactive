package joe.sync.mapper;

import joe.reactive.sixth.SixthDto;
import joe.sync.entity.ReactiveFields;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields mapToEntity(SixthDto fifthDto, String type);
    default SixthDto mapToDto(Optional<ReactiveFields> reactiveFields){
        return reactiveFields.map(this::mapToDto).orElse(null);
    }
    SixthDto mapToDto(ReactiveFields reactiveFields);
}
