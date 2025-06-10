package redis

import joe.model.FourthDto
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import redis.mapper.FourthMapper
import redis.service.RedisMessagePublisher
import redis.service.RedisMessageSubscriber

fun main(args: Array<String>) {
    runApplication<FourthRedisApplication>(*args)
}

@SpringBootApplication
open class FourthRedisApplication {
    @Bean
    open fun topic(): ChannelTopic {
        return ChannelTopic("fifth_topic")
    }

    @Bean
    open fun messageListener(redisMessagePublisher: RedisMessagePublisher, mapper: FourthMapper): MessageListenerAdapter{
        val messageListenerAdapter = MessageListenerAdapter(RedisMessageSubscriber(redisMessagePublisher, mapper))
        messageListenerAdapter.setSerializer(Jackson2JsonRedisSerializer(FourthDto::class.java))
        return messageListenerAdapter
    }

    @Bean
    open fun redisContainer(redisMessagePublisher: RedisMessagePublisher, lettuceConnectionFactory: LettuceConnectionFactory, mapper: FourthMapper): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.connectionFactory = lettuceConnectionFactory
        container.addMessageListener(messageListener(redisMessagePublisher, mapper), topic())
        return container
    }
}