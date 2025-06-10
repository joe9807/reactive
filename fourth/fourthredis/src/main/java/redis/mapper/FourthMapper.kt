package redis.mapper

import joe.model.FifthDto
import joe.model.FourthDto
import org.springframework.stereotype.Component

@Component
class FourthMapper {
    fun map(fourthDto: FourthDto): FifthDto {
        return FifthDto.builder()
            .field11111(fourthDto.field1111)
            .field22222(fourthDto.field2222)
            .field33333(fourthDto.field3333)
            .field44444(fourthDto.field4444)
            .build()
    }
}