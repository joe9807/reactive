package redis

import joe.model.SecondDto
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import redis.mapper.SecondMapper
import redis.service.RedisMessagePublisher
import redis.service.RedisMessageSubscriber

fun main(args: Array<String>) {
    runApplication<SecondRedisApplication>(*args)
}

@SpringBootApplication
open class SecondRedisApplication {
    @Bean
    open fun inTopic(): ChannelTopic {
        return ChannelTopic("second_topic")
    }

    @Bean
    open fun outTopic(): ChannelTopic {
        return ChannelTopic("third_topic")
    }

    @Bean
    open fun messageListener(redisMessagePublisher: RedisMessagePublisher, mapper: SecondMapper): MessageListenerAdapter{
        val messageListenerAdapter = MessageListenerAdapter(RedisMessageSubscriber(redisMessagePublisher, mapper))
        messageListenerAdapter.setSerializer(Jackson2JsonRedisSerializer(SecondDto::class.java))
        return messageListenerAdapter
    }

    @Bean
    open fun redisContainer(redisMessagePublisher: RedisMessagePublisher, lettuceConnectionFactory: LettuceConnectionFactory, mapper: SecondMapper): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = lettuceConnectionFactory
        container.addMessageListener(messageListener(redisMessagePublisher, mapper), inTopic())
        return container
    }
}