package joe.grpc.mapper;

import joe.grpc.entity.ReactiveFields;
import joe.model.Api;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SixthMapper {
    ReactiveFields dtoToEntity(Api.SixthDto fifthDto, String type);
    Api.SixthDto entityToDto(ReactiveFields entity);
}
