package joe.grpc.service;

import com.google.protobuf.Struct;
import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.FourthMapper;
import joe.model.Api;
import joe.model.FifthServiceGrpc;
import joe.model.FourthServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@GrpcService
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.grpc.mapper")
public class FourthServiceImpl extends FourthServiceGrpc.FourthServiceImplBase {
    private final FourthMapper mapper;

    @GrpcClient("fifth-service")
    private FifthServiceGrpc.FifthServiceStub fifthServiceStub;

    @Override
    public void process(Api.FourthDto request, StreamObserver<Struct> responseObserver) {
        log.info("request: {}", request);
        fifthServiceStub.process(mapper.map(request), responseObserver);
    }
}
