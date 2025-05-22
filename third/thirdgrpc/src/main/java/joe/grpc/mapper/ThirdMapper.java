package joe.grpc.mapper;

import joe.model.Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThirdMapper {
    default Api.FourthDto map(Api.ThirdDto thirdDto) {
        return Api.FourthDto.newBuilder()
                .setField1111(thirdDto.getField111())
                .setField2222(thirdDto.getField222())
                .setField3333(thirdDto.getField333())
                .setField4444(thirdDto.getField444())
                .build();
    }
}
