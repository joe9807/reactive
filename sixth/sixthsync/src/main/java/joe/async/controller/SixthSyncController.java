package joe.async.controller;

import joe.reactive.sixth.SixthDto;
import joe.async.entity.ReactiveFields;
import joe.async.mapper.SixthMapper;
import joe.async.repository.CrudFieldsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("sync/sixth")
@RequiredArgsConstructor
public class SixthSyncController {
    private final CrudFieldsRepository repository;
    private final SixthMapper mapper;

    @PostMapping("process")
    public ReactiveFields process(@RequestBody SixthDto sixthDto){
        log.info("SYNC: dto: {}", sixthDto);
        return repository.save(mapper.map(sixthDto, "SYNC"));
    }
}
