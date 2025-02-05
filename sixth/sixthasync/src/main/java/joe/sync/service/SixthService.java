package joe.sync.service;

import io.micrometer.core.instrument.MeterRegistry;
import joe.sync.entity.ReactiveFields;
import joe.reactive.sixth.SixthDto;
import joe.sync.mapper.SixthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SixthService {
    private final SixthMapper mapper;
    private final R2dbcRepository<ReactiveFields, Long> repository;
    private final MeterRegistry meterRegistry;

    public Mono<ReactiveFields> process(SixthDto sixthDto){
        log.info("ASYNC: dto: {}", sixthDto);

        return repository.save(mapper.map(sixthDto, "ASYNC"))
                .doOnError(e->{
                    log.error("Send Failed {}", e.getMessage());
                    meterRegistry.counter("r2dbc.repository.error").increment();
                })
                .doOnSuccess(unused -> meterRegistry.counter("r2dbc.repository.request").increment())
                .onErrorComplete();
    }
}
