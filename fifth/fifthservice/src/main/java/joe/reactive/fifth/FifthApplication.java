package joe.reactive.fifth;

import io.netty.channel.ChannelOption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@SpringBootApplication
public class FifthApplication {

	public static void main(String[] args) {
		SpringApplication.run(FifthApplication.class, args);
	}

	@Bean
	public WebClient webClient(){
		HttpClient httpClient = HttpClient.create(ConnectionProvider.builder("custom")
						.maxConnections(1000) // увеличьте это значение
						.pendingAcquireMaxCount(20000) // увеличьте это значение
						.pendingAcquireTimeout(Duration.ofMinutes(100))
						.build())
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				.responseTimeout(Duration.ofSeconds(1000));

		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
}
