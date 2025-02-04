package joe.async.mapper;

import joe.model.FifthDto;
import joe.model.FourthDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface FourthMapper {
    default FifthDto map(FourthDto fourthDto, String callbackUrl) {
        return FifthDto.builder()
                .field11111(fourthDto.getField1111())
                .field22222(fourthDto.getField2222())
                .field33333(fourthDto.getField3333())
                .field44444(fourthDto.getField4444())
                .start(LocalDateTime.now())
                .callbackUrl(fourthDto.getCallbackUrl() != null?callbackUrl:null)
                .build();
    }
}
