package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.grpc.entity.ReactiveFields;
import joe.grpc.mapper.SixthMapper;
import joe.grpc.repository.CrudFieldsRepository;
import joe.model.Api;
import joe.model.SixthServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class SixthServiceImpl extends SixthServiceGrpc.SixthServiceImplBase {
    private final SixthMapper mapper;
    private final CrudFieldsRepository repository;

    @Override
    public void process6(Api.SixthDto request, StreamObserver<Api.SixthDto> responseObserver) {
        log.info("request: {}", request);
        ReactiveFields reactiveFields = repository.save(mapper.dtoToEntity(request, "GRPC"));
        responseObserver.onNext(mapper.entityToDto(reactiveFields));
        responseObserver.onCompleted();
    }
}
