package redis.controller

import joe.model.FirstDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import redis.service.RedisMessagePublisherInternal

@RestController
@RequestMapping("redis")
class RedisController (private val redisMessagePublisher: RedisMessagePublisherInternal){
    @PostMapping("send")
    fun send(@RequestBody firstDto: FirstDto){
        redisMessagePublisher.publish(firstDto)
    }
}