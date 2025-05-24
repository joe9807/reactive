package joe.grpc.service;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
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
    public void process(Api.SixthDto request, StreamObserver<Struct> responseObserver) {
        log.info("request: {}", request);
        ReactiveFields reactiveFields = repository.save(mapper.dtoToEntity(request, "GRPC"));

        Struct struct = Struct.newBuilder()
                .putFields("field111111", Value.newBuilder().setStringValue(reactiveFields.getField111111()).build())
                .putFields("field222222", Value.newBuilder().setStringValue(reactiveFields.getField222222()).build())
                .putFields("field333333", Value.newBuilder().setStringValue(reactiveFields.getField333333()).build())
                .putFields("field444444", Value.newBuilder().setStringValue(reactiveFields.getField444444()).build())
                .build();
        responseObserver.onNext(struct);
        responseObserver.onCompleted();
    }
}
