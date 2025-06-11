package redis.service

import joe.reactive.fourth.FourthDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisMessagePublisher (private val redisTemplate: RedisTemplate<String, FourthDto>, private val outTopic: ChannelTopic){
    fun publish(fourthDto: FourthDto){
        redisTemplate.convertAndSend(outTopic.topic, fourthDto)
    }
}