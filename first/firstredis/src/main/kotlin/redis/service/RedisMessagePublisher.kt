package redis.service

import joe.reactive.second.dto.SecondDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisMessagePublisher (private val redisTemplate: RedisTemplate<String, SecondDto>, private val topic: ChannelTopic){

    fun publish(secondDto: SecondDto){
        redisTemplate.convertAndSend(topic.topic, secondDto)
    }
}