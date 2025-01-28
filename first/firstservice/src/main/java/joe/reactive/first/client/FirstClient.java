package joe.reactive.first.client;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.second.dto.SecondDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirstClient {
    private final WebClient webClient;

    public Mono<JsonNode> next(SecondDto secondDto){
        return webClient
                .post()
                .bodyValue(secondDto)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                .onErrorComplete();
    }
}
