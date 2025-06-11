package redis.config

import joe.model.FirstDto
import joe.reactive.second.dto.SecondDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
open class RedisConfig {
    @Bean
    open fun redisTemplateInternal(redisConnectionFactory: RedisConnectionFactory):RedisTemplate<String, FirstDto> {
        val template: RedisTemplate<String, FirstDto> = RedisTemplate()
        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = Jackson2JsonRedisSerializer(FirstDto::class.java)
        return template
    }

    @Bean
    open fun redisTemplate(redisConnectionFactory: RedisConnectionFactory):RedisTemplate<String, SecondDto> {
        val template: RedisTemplate<String, SecondDto> = RedisTemplate()
        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = Jackson2JsonRedisSerializer(SecondDto::class.java)
        return template
    }
}