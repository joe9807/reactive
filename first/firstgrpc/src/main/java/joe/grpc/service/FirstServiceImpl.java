package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.FirstMapper;
import joe.model.Api;
import joe.model.FirstServiceGrpc;
import joe.model.SecondServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class FirstServiceImpl extends FirstServiceGrpc.FirstServiceImplBase {
    private final FirstMapper mapper;

    @GrpcClient("second-service")
    private SecondServiceGrpc.SecondServiceStub secondServiceStub;

    @Override
    public void process1(Api.FirstDto request, StreamObserver<Api.FourthDto> responseObserver) {
        log.info("request: {}", request);
        secondServiceStub.process2(mapper.map(request), responseObserver);
    }
}
