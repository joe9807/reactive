package joe.grpc.service;

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
    public void process5(Api.FifthDto request, StreamObserver<Api.SixthDto> responseObserver) {
        log.info("request: {}", request);
        sixthServiceStub.process6(mapper.map(request), responseObserver);
    }
}
