package redis.controller

import joe.model.FirstDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import redis.mapper.FirstMapper
import redis.service.RedisMessagePublisher

@RestController
@RequestMapping("redis")
class RedisController (private val redisMessagePublisher: RedisMessagePublisher, private val mapper: FirstMapper){
    @PostMapping("send")
    fun send(@RequestBody firstDto: FirstDto){
        redisMessagePublisher.publish(mapper.map(firstDto))
    }
}