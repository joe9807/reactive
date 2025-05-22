package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.SecondMapper;
import joe.model.Api;
import joe.model.SecondServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class SecondServiceImpl extends SecondServiceGrpc.SecondServiceImplBase {
    private final SecondMapper mapper;

    @Override
    public void process2(Api.SecondDto request, StreamObserver<Api.ThirdDto> responseObserver) {
        log.info("request: {}", request);
        responseObserver.onNext(mapper.map(request));
        responseObserver.onCompleted();
    }
}
