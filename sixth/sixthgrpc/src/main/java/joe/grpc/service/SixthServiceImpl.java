package joe.grpc.service;

import io.grpc.stub.StreamObserver;
import joe.model.Api;
import joe.model.SixthServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class SixthServiceImpl extends SixthServiceGrpc.SixthServiceImplBase {

    @Override
    public void process6(Api.SixthDto request, StreamObserver<Api.SixthDto> responseObserver) {
        log.info("request: {}", request);
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}
