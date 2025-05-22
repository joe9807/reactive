package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.ThirdMapper;
import joe.model.Api;
import joe.model.ThirdServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class ThirdServiceImpl extends ThirdServiceGrpc.ThirdServiceImplBase {
    private final ThirdMapper mapper;

    @Override
    public void process3(Api.ThirdDto request, StreamObserver<Api.FourthDto> responseObserver) {
        log.info("request: {}", request);
        //secondServiceStub.process2(mapper.map(request), responseObserver);
        responseObserver.onNext(mapper.map(request));
        responseObserver.onCompleted();
    }
}
