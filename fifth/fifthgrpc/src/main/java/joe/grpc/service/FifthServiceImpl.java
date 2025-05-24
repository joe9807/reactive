package joe.grpc.service;

import com.google.protobuf.Struct;
import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.FifthMapper;
import joe.model.Api;
import joe.model.FifthServiceGrpc;
import joe.model.SixthServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class FifthServiceImpl extends FifthServiceGrpc.FifthServiceImplBase {
    private final FifthMapper mapper;

    @GrpcClient("sixth-service")
    private SixthServiceGrpc.SixthServiceStub sixthServiceStub;

    @Override
    public void process(Api.FifthDto request, StreamObserver<Struct> responseObserver) {
        log.info("request: {}", request);
        sixthServiceStub.process(mapper.map(request), responseObserver);
    }
}
