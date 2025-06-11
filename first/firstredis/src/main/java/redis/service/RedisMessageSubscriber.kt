package redis.service

import joe.model.FirstDto
import redis.mapper.FirstMapper

class RedisMessageSubscriber(private val redisMessagePublisher: RedisMessagePublisher, private val mapper: FirstMapper) {
    fun handleMessage(firstDto: FirstDto){
        redisMessagePublisher.publish(mapper.map(firstDto))
    }
}