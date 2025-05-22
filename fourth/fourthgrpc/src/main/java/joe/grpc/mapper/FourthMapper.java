package joe.grpc.mapper;

import joe.model.Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FourthMapper {
    default Api.FifthDto map(Api.FourthDto fourthDto) {
        return Api.FifthDto.newBuilder()
                .setField11111(fourthDto.getField1111())
                .setField22222(fourthDto.getField2222())
                .setField33333(fourthDto.getField3333())
                .setField44444(fourthDto.getField4444())
                .build();
    }
}
