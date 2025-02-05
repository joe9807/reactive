package joe.sync.mapper;

import joe.reactive.dto.ThirdDto;
import joe.reactive.fourth.FourthDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ThirdMapper {
    default FourthDto map(ThirdDto thirdDto, String callbackUrl) {
        return FourthDto.builder()
                .field1111(thirdDto.getField111())
                .field2222(thirdDto.getField222())
                .field3333(thirdDto.getField333())
                .field4444(thirdDto.getField444())
                .start(LocalDateTime.now())
                .callbackUrl(thirdDto.isNeedCallback()?callbackUrl:null)
                .build();
    }
}
