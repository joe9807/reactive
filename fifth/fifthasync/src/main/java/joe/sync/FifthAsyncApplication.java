package joe.sync;

import io.netty.channel.ChannelOption;
import joe.sync.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@SpringBootApplication
public class FifthAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(FifthAsyncApplication.class, args);
	}

	@Bean
	public WebClient webClient(AppConfig appConfig){
		HttpClient httpClient = HttpClient.create(ConnectionProvider.builder("custom")
						.maxConnections(1000) // увеличьте это значение
						.pendingAcquireMaxCount(20000) // увеличьте это значение
						.pendingAcquireTimeout(Duration.ofMinutes(100))
						.build())
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				.responseTimeout(Duration.ofSeconds(1000));

		return WebClient.builder()
				.baseUrl(appConfig.getNext().getUrl())
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
}
