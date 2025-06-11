package redis.service

import joe.reactive.dto.ThirdDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisMessagePublisher (private val redisTemplate: RedisTemplate<String, ThirdDto>, private val outTopic: ChannelTopic){
    fun publish(thirdDto: ThirdDto){
        redisTemplate.convertAndSend(outTopic.topic, thirdDto)
    }
}