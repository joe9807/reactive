package redis;

import joe.model.FifthDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.mapper.FifthMapper;
import redis.service.RedisMessagePublisher;
import redis.service.RedisMessageSubscriber;

@SpringBootApplication
public class FifthRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FifthRedisApplication.class, args);
	}

	@Bean
	ChannelTopic inTopic() {
		return new ChannelTopic("fifth_topic");
	}

	@Bean
	ChannelTopic outTopic() {
		return new ChannelTopic("sixth_topic");
	}

	@Bean
	MessageListenerAdapter messageListener(RedisMessagePublisher redisMessagePublisher, FifthMapper mapper) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new RedisMessageSubscriber(redisMessagePublisher, mapper));
		messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(FifthDto.class));
		return messageListenerAdapter;
	}

	@Bean
	RedisMessageListenerContainer redisContainer(LettuceConnectionFactory lettuceConnectionFactory, RedisMessagePublisher redisMessagePublisher, FifthMapper mapper) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(lettuceConnectionFactory);
		container.addMessageListener(messageListener(redisMessagePublisher, mapper), inTopic());
		return container;
	}
}
