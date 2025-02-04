package joe.async.mapper;

import joe.reactive.dto.ThirdDto;
import joe.model.SecondDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecondMapper {
    default ThirdDto map(SecondDto secondDto) {
        return ThirdDto.builder()
                .field111(secondDto.getField11())
                .field222(secondDto.getField22())
                .field333(secondDto.getField33())
                .field444(secondDto.getField44())
                .needCallback(secondDto.isNeedCallback())
                .build();
    }
}
