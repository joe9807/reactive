package joe.grpc.mapper;

import joe.model.Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FifthMapper {
    default Api.SixthDto map(Api.FifthDto fifthDto) {
        return Api.SixthDto.newBuilder()
                .setField111111(fifthDto.getField11111())
                .setField222222(fifthDto.getField22222())
                .setField333333(fifthDto.getField33333())
                .setField444444(fifthDto.getField44444())
                .build();
    }
}
