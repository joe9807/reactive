package redis.service

import joe.reactive.dto.ThirdDto
import redis.mapper.ThirdMapper

class RedisMessageSubscriber(private val redisMessagePublisher: RedisMessagePublisher, private val mapper: ThirdMapper) {
    fun handleMessage(thirdDto: ThirdDto){
        redisMessagePublisher.publish(mapper.map(thirdDto))
    }
}