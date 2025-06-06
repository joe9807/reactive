package joe.sync.controller;

import joe.reactive.sixth.SixthDto;
import joe.sync.service.SixthSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("sync/sixth")
@RequiredArgsConstructor
public class SixthSyncController {
    private final SixthSyncService sixthSyncService;

    @PostMapping("process")
    public SixthDto process(@RequestBody SixthDto sixthDto){
        log.debug("SYNC: dto: {}", sixthDto);
        return sixthSyncService.process(sixthDto);
    }

    @GetMapping("find/{id}")
    public SixthDto findById(@PathVariable Long id){
        return sixthSyncService.findById(id);
    }
}
