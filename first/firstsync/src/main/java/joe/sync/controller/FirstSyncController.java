package joe.sync.controller;

import com.fasterxml.jackson.databind.JsonNode;
import joe.model.FirstDto;
import joe.sync.config.AppConfig;
import joe.sync.mapper.FirstMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@RestController
@RequestMapping("sync/first")
@RequiredArgsConstructor
@ComponentScan(basePackages = "joe.model.mapper")
public class FirstSyncController {
    private final FirstMapper mapper;
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    @PostMapping("process")
    public JsonNode process(@RequestBody FirstDto firstDto){
        log.debug("SYNC: dto: {}", firstDto);
        return restTemplate.postForObject(URI.create(appConfig.getNext().getUrl()), mapper.map(firstDto), JsonNode.class);
    }

    @GetMapping("futures")
    public CompletionStage<String> futures(){
        return CompletableFuture.supplyAsync(()-> {
            log.info(Thread.currentThread().getName()+" - supplyAsync");
            return "supplyAsync";
        }).thenCombineAsync(CompletableFuture.runAsync(() -> {
            log.info(Thread.currentThread().getName()+" - thenCombineAsync(runAsync)");
        }), (t, u)->{
            log.info(Thread.currentThread().getName()+" - thenCombineAsync");
            return t+(u == null?" runAsync":u);
        })
        .thenApplyAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenApplyAsync0");
            return v+" thenApplyAsync0";
        }).thenApplyAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenApplyAsync1");
            return v+" thenApplyAsync1";
        }).thenApplyAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenApplyAsync2");
            return v+" thenApplyAsync2";
        }).thenComposeAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenComposeAsync");
            return CompletableFuture.supplyAsync(() -> {
                log.info(Thread.currentThread().getName()+" - thenComposeAsync(supplyAsync)");
                return v+" thenComposeAsync(supplyAsync)";
            });
        }).thenCombineAsync(
            CompletableFuture.supplyAsync(() -> {
                log.info(Thread.currentThread().getName()+" - thenCombineAsync(supplyAsync)");
                return " thenCombineAsync(supplyAsync)";
            }), (t, u)->{
                log.info(Thread.currentThread().getName()+" - thenCombineAsync0");
                return t+u;
        }).thenApplyAsync(result->{
            log.info("--------------------------------------------------- thenApplyAsync");
            return result;
        });
    }
}
