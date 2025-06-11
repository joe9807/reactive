package redis.mapper

import joe.model.FirstDto
import joe.reactive.second.dto.SecondDto
import org.springframework.stereotype.Component

@Component
class FirstMapper {
    fun map(firstDto: FirstDto): SecondDto {
        return SecondDto.builder()
            .field11(firstDto.field1)
            .field22(firstDto.field2)
            .field33(firstDto.field3)
            .field44(firstDto.field4)
            .build()
    }
}