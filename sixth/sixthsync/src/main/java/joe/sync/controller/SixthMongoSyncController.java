package joe.sync.controller;

import joe.reactive.sixth.SixthDto;
import joe.sync.service.SixthSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("sync/sixth/mongo")
@RequiredArgsConstructor
public class SixthMongoSyncController {
    private final SixthSyncService sixthSyncService;

    @PostMapping("process")
    public SixthDto process(@RequestBody SixthDto sixthDto){
        log.debug("SYNC: dto: {}", sixthDto);
        return sixthSyncService.processMongo(sixthDto);
    }

    @GetMapping("find/{id}")
    public SixthDto findById(@PathVariable String id){
        return sixthSyncService.findByIdMongo(id);
    }
}
