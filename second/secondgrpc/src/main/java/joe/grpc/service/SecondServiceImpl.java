package joe.grpc.service;

import com.google.protobuf.Struct;
import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.SecondMapper;
import joe.model.Api;
import joe.model.SecondServiceGrpc;
import joe.model.ThirdServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class SecondServiceImpl extends SecondServiceGrpc.SecondServiceImplBase {
    private final SecondMapper mapper;

    @GrpcClient("third-service")
    private ThirdServiceGrpc.ThirdServiceStub secondServiceStub;

    @Override
    public void process(Api.SecondDto request, StreamObserver<Struct> responseObserver) {
        log.info("request: {}", request);
        secondServiceStub.process(mapper.map(request), responseObserver);
    }
}
