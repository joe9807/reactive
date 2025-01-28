package joe.reactive.sixth.service;

import io.micrometer.core.instrument.MeterRegistry;
import joe.reactive.sixth.SixthDto;
import joe.reactive.sixth.entity.ReactiveFields;
import joe.reactive.sixth.mapper.SixthMapper;
import joe.reactive.sixth.repository.ReactiveFieldsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SixthService {
    private final SixthMapper mapper;
    private final ReactiveFieldsRepository repository;
    private final MeterRegistry meterRegistry;

    public Mono<ReactiveFields> process(SixthDto sixthDto){
        log.info("process: {}", sixthDto);

        return repository.save(mapper.map(sixthDto))
                .doOnError(e->{
                    log.error("Send Failed {}", e.getMessage());
                    meterRegistry.counter("r2dbc.repository.error").increment();
                })
                .doOnSuccess(unused -> meterRegistry.counter("r2dbc.repository.request").increment())
                .onErrorComplete();
    }
}
