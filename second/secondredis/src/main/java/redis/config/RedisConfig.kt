package redis.config

import joe.reactive.dto.ThirdDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
open class RedisConfig() {
    @Bean
    open fun redisTemplate(redisConnectionFactory: RedisConnectionFactory):RedisTemplate<String, ThirdDto> {
        val template: RedisTemplate<String, ThirdDto> = RedisTemplate()
        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = Jackson2JsonRedisSerializer(ThirdDto::class.java)
        return template
    }
}