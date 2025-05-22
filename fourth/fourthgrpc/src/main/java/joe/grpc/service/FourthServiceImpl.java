package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.FourthMapper;
import joe.model.Api;
import joe.model.FourthServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class FourthServiceImpl extends FourthServiceGrpc.FourthServiceImplBase {
    private final FourthMapper mapper;

    @Override
    public void process4(Api.FourthDto request, StreamObserver<Api.FifthDto> responseObserver) {
        log.info("request: {}", request);
        responseObserver.onNext(mapper.map(request));
        responseObserver.onCompleted();
    }
}
