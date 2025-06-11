package redis.service

import joe.model.SecondDto
import redis.mapper.SecondMapper

class RedisMessageSubscriber(private val redisMessagePublisher: RedisMessagePublisher, private val mapper: SecondMapper) {
    fun handleMessage(secondDto: SecondDto){
        redisMessagePublisher.publish(mapper.map(secondDto))
    }
}