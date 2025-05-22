package joe.grpc.mapper;

import joe.model.Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecondMapper {
    default Api.ThirdDto map(Api.SecondDto secondDto) {
        return Api.ThirdDto.newBuilder()
                .setField111(secondDto.getField11())
                .setField222(secondDto.getField22())
                .setField333(secondDto.getField33())
                .setField444(secondDto.getField44())
                .build();
    }
}
