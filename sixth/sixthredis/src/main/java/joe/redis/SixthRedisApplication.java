package joe.redis;

import joe.reactive.sixth.SixthDto;
import joe.redis.config.AppConfig;
import joe.redis.mapper.SixthMapper;
import joe.redis.repository.CrudFieldsRepository;
import joe.redis.service.RedisMessageSubscriber;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@SpringBootApplication
public class SixthRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SixthRedisApplication.class, args);
	}

	@Bean
	MessageListenerAdapter messageListener(CrudFieldsRepository repository, SixthMapper mapper, RedissonClient redissonClient, AppConfig appConfig) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new RedisMessageSubscriber(repository, mapper, redissonClient, appConfig));
		messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(SixthDto.class));
		return messageListenerAdapter;
	}

	@Bean
	RedisMessageListenerContainer redisContainer(LettuceConnectionFactory lettuceConnectionFactory, CrudFieldsRepository repository, SixthMapper mapper, RedissonClient redissonClient, AppConfig appConfig) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(lettuceConnectionFactory);
		container.addMessageListener(messageListener(repository, mapper, redissonClient, appConfig), new ChannelTopic("sixth_topic"));
		return container;
	}

	@Bean
	RedissonClient redissonClient(){
		return Redisson.create();
	}
}
