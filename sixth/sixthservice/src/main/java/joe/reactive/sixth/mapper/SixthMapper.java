package joe.reactive.sixth.mapper;

import joe.reactive.sixth.SixthDto;
import joe.reactive.sixth.entity.ReactiveFields;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SixthMapper {
    ReactiveFields map(SixthDto fifthDto);
}
