package joe.reactive.second.client;

import com.fasterxml.jackson.databind.JsonNode;
import joe.reactive.dto.ThirdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecondClient {
    private final WebClient webClient;

    public Mono<JsonNode> next(ThirdDto thirdDto){
        return webClient
                .post()
                .bodyValue(thirdDto)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .doOnError(e-> log.error("Send failed {}", e.getMessage()))
                .onErrorComplete();
    }
}
