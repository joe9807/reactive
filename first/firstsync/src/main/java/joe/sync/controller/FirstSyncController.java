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
import java.util.function.BiFunction;
import java.util.function.Function;

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
        }).thenCombineAsync(CompletableFuture.runAsync(() -> log.info(Thread.currentThread().getName()+" - thenCombineAsync0(runAsync)")), (t, u)->{
            log.info(Thread.currentThread().getName()+" - thenCombineAsync0");
            return t+ " thenCombineAsync0(runAsync)";
        }).thenApplyAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenApplyAsync0");
            return v+" thenApplyAsync0";
        }).thenApplyAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenApplyAsync1");
            return v+" thenApplyAsync1";
        }).thenApplyAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenApplyAsync2");
            return v+" thenApplyAsync2";
        }).thenComposeAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenComposeAsync0");
            return CompletableFuture.supplyAsync(() -> {
                log.info(Thread.currentThread().getName()+" - thenComposeAsync0(supplyAsync)");
                return v+" thenComposeAsync0(supplyAsync)";
            });
        }).thenComposeAsync(v->{
            log.info(Thread.currentThread().getName()+" - thenComposeAsync1");
            return CompletableFuture.supplyAsync(() -> {
                log.info(Thread.currentThread().getName()+" - thenComposeAsync1(supplyAsync)");
                return v+" thenComposeAsync1(supplyAsync)";
            });
        }).thenCombineAsync(
            CompletableFuture.supplyAsync(() -> {
                log.info(Thread.currentThread().getName()+" - thenCombineAsync1(supplyAsync)");
                return " thenCombineAsync1(supplyAsync)";
            }), (t, u)->{
                log.info(Thread.currentThread().getName()+" - thenCombineAsync1");
                return t+u;
        }).handle(new HandleFunction())
                .exceptionally(new ExceptionallyFunction());
    }

    public String handleMethod(String result, Throwable throwable) {
        return "handleMethod";
    }

    public String exceptionallyMethod(Throwable throwable) {
        return "exceptionallyMethod";
    }

    public static class HandleFunction implements BiFunction<String, Throwable, String> {
        @Override
        public String apply(String result, Throwable ex) {
            log.info("--------------------------------------------------- handle");
            if (ex != null) {
                log.info("Exception: ", ex);
            }
            if (Math.random()>0.5) {
                 log.info("Exception was thrown");
                throw new RuntimeException(ex!=null?ex.getMessage():"Manual Exception");
            }
            return result+" handle";
        }
    }

    public static class ExceptionallyFunction implements Function<Throwable, String> {
        @Override
        public String apply(Throwable throwable) {
            return "exceptionallyClass";
        }
    }
}
