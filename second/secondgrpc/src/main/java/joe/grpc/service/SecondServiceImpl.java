package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.model.Api;
import joe.model.SecondServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@GrpcService
public class SecondServiceImpl extends SecondServiceGrpc.SecondServiceImplBase {
    @Value("${next.port}")
    private Integer nextPort;

    @Override
    public void process2(Api.SecondDto request, StreamObserver<Api.ThirdDto> responseObserver) {
        log.info("request: {}", request);
        responseObserver.onNext(Api.ThirdDto.newBuilder()
                        .setField111(request.getField11())
                        .setField222(request.getField22())
                        .setField333(request.getField33())
                        .setField444(request.getField44())
                .build());
        responseObserver.onCompleted();
    }
}
