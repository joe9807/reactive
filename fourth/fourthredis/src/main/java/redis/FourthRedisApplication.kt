package redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.listener.ChannelTopic

fun main(args: Array<String>) {
    runApplication<FourthRedisApplication>(*args)
}

@SpringBootApplication
open class FourthRedisApplication {
    @Bean
    open fun topic(): ChannelTopic {
        return ChannelTopic("fifth_topic")
    }
}