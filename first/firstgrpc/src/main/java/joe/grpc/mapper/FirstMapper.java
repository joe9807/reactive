package joe.grpc.mapper;

import joe.model.Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FirstMapper {
    default Api.SecondDto map(Api.FirstDto firstDto) {
        return Api.SecondDto.newBuilder()
                .setField11(firstDto.getField1())
                .setField22(firstDto.getField2())
                .setField33(firstDto.getField3())
                .setField44(firstDto.getField4())
                .build();
    }
}
