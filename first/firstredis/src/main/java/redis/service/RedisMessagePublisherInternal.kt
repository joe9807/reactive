package redis.service

import joe.model.FirstDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisMessagePublisherInternal (private val redisTemplateInternal: RedisTemplate<String, FirstDto>, private val internalTopic: ChannelTopic){

    fun publish(firstDto: FirstDto){
        redisTemplateInternal.convertAndSend(internalTopic.topic, firstDto)
    }
}