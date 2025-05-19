package joe.grpc.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import joe.grpc.mapper.FirstMapper;
import joe.model.Api;
import joe.model.FirstServiceGrpc;
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
public class FirstServiceImpl extends FirstServiceGrpc.FirstServiceImplBase {
    private final FirstMapper mapper;

    @Value("${next.port}")
    private Integer nextPort;

    @Override
    public void process1(Api.FirstDto request, StreamObserver<Api.ThirdDto> responseObserver) {
        log.info("request: {}", request);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", nextPort).usePlaintext().build();

        SecondServiceGrpc.newStub(channel).process2(mapper.map(request), responseObserver);
    }
}
