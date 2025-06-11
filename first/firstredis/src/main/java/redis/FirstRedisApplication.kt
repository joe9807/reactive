package redis

import joe.model.FirstDto
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import redis.mapper.FirstMapper
import redis.service.RedisMessagePublisher
import redis.service.RedisMessageSubscriber

fun main(args: Array<String>) {
    runApplication<FirstRedisApplication>(*args)
}

@SpringBootApplication
open class FirstRedisApplication {
    @Bean
    open fun internalTopic(): ChannelTopic {
        return ChannelTopic("first_topic")
    }

    @Bean
    open fun outTopic(): ChannelTopic {
        return ChannelTopic("second_topic")
    }

    @Bean
    open fun messageListener(redisMessagePublisher: RedisMessagePublisher, mapper: FirstMapper): MessageListenerAdapter {
        val messageListenerAdapter = MessageListenerAdapter(RedisMessageSubscriber(redisMessagePublisher, mapper))
        messageListenerAdapter.setSerializer(Jackson2JsonRedisSerializer(FirstDto::class.java))
        return messageListenerAdapter
    }

    @Bean
    open fun redisContainer(redisMessagePublisher: RedisMessagePublisher, lettuceConnectionFactory: LettuceConnectionFactory, mapper: FirstMapper): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = lettuceConnectionFactory
        container.addMessageListener(messageListener(redisMessagePublisher, mapper), internalTopic())
        return container
    }
}