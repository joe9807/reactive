package joe.async.mapper;

import joe.model.FifthDto;
import joe.reactive.sixth.SixthDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FifthMapper {
    default SixthDto map(FifthDto fifthDto) {
        return SixthDto.builder()
                .field111111(fifthDto.getField11111())
                .field222222(fifthDto.getField22222())
                .field333333(fifthDto.getField33333())
                .field444444(fifthDto.getField44444())
                .build();
    }
}
