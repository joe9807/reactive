package joe.sync.mapper;

import joe.model.FirstDto;
import joe.reactive.second.dto.SecondDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FirstMapper {
    default SecondDto map(FirstDto firstDto) {
        return SecondDto.builder()
                .field11(firstDto.getField1())
                .field22(firstDto.getField2())
                .field33(firstDto.getField3())
                .field44(firstDto.getField4())
                .needCallback(firstDto.isNeedCallback())
                .build();
    }
}
