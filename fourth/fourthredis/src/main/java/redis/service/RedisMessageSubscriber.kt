package redis.service

import joe.model.FourthDto
import redis.mapper.FourthMapper

class RedisMessageSubscriber(private val redisMessagePublisher: RedisMessagePublisher, private val mapper: FourthMapper) {
    fun handleMessage(fourthDto: FourthDto){
        redisMessagePublisher.publish(mapper.map(fourthDto))
    }
}