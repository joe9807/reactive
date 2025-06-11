package redis

import joe.reactive.dto.ThirdDto
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import redis.mapper.ThirdMapper
import redis.service.RedisMessagePublisher
import redis.service.RedisMessageSubscriber

fun main(args: Array<String>) {
    runApplication<ThirdRedisApplication>(*args)
}

@SpringBootApplication
open class ThirdRedisApplication {
    @Bean
    open fun inTopic(): ChannelTopic {
        return ChannelTopic("third_topic")
    }

    @Bean
    open fun outTopic(): ChannelTopic {
        return ChannelTopic("fourth_topic")
    }

    @Bean
    open fun messageListener(redisMessagePublisher: RedisMessagePublisher, mapper: ThirdMapper): MessageListenerAdapter{
        val messageListenerAdapter = MessageListenerAdapter(RedisMessageSubscriber(redisMessagePublisher, mapper))
        messageListenerAdapter.setSerializer(Jackson2JsonRedisSerializer(ThirdDto::class.java))
        return messageListenerAdapter
    }

    @Bean
    open fun redisContainer(redisMessagePublisher: RedisMessagePublisher, lettuceConnectionFactory: LettuceConnectionFactory, mapper: ThirdMapper): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = lettuceConnectionFactory
        container.addMessageListener(messageListener(redisMessagePublisher, mapper), inTopic())
        return container
    }
}