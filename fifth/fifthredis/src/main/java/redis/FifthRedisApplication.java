package redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.ChannelTopic;

@SpringBootApplication
public class FifthRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FifthRedisApplication.class, args);
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("joe_queue");
	}
}
