package redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.listener.ChannelTopic

fun main(args: Array<String>) {
    runApplication<FirstRedisApplication>(*args)
}

@SpringBootApplication
open class FirstRedisApplication {
    @Bean
    open fun topic(): ChannelTopic {
        return ChannelTopic("second_topic")
    }
}