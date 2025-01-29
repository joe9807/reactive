package joe.reactive.sixth.controller;

import joe.reactive.sixth.SixthDto;
import joe.reactive.sixth.entity.ReactiveFields;
import joe.reactive.sixth.mapper.SixthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("sync/sixth")
@RequiredArgsConstructor
public class SixthSyncController {
    private final R2dbcRepository<ReactiveFields, Long> repository;
    private final SixthMapper mapper;

    @PostMapping("process")
    public ReactiveFields process(@RequestBody SixthDto sixthDto){
        return repository.save(mapper.map(sixthDto, "SYNC")).toFuture().join();
    }
}
