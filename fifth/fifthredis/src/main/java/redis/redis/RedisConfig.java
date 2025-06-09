package redis.redis;


import joe.reactive.sixth.SixthDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Сериализатор для ключей (String)
        template.setKeySerializer(new StringRedisSerializer());

        // Сериализатор для значений (JSON)
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(SixthDto.class));

        // Сериализатор для хэш-ключей
        template.setHashKeySerializer(new StringRedisSerializer());

        // Сериализатор для хэш-значений
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}