package redis.controller

import joe.model.FourthDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import redis.mapper.FifthMapper
import redis.service.RedisMessagePublisher

@RestController
@RequestMapping("redis")
class RedisController (private val redisMessagePublisher: RedisMessagePublisher, private val mapper: FifthMapper){
    @PostMapping("send")
    fun send(@RequestBody fourthDto: FourthDto){
        redisMessagePublisher.publish(mapper.map(fourthDto))
    }
}