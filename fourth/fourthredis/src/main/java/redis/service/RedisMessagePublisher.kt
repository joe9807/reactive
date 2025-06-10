package redis.service

import joe.model.FifthDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisMessagePublisher (private val redisTemplate: RedisTemplate<String, FifthDto>, private val topic: ChannelTopic){

    fun publish(fifthDto: FifthDto){
        redisTemplate.convertAndSend(topic.topic, fifthDto)
    }
}