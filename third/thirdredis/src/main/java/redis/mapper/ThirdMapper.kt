package redis.mapper

import joe.reactive.dto.ThirdDto
import joe.reactive.fourth.FourthDto
import org.springframework.stereotype.Component

@Component
class ThirdMapper {
    fun map(thirdDto: ThirdDto): FourthDto {
        return FourthDto.builder()
            .field1111(thirdDto.field111)
            .field2222(thirdDto.field222)
            .field3333(thirdDto.field333)
            .field4444(thirdDto.field444)
            .build()
    }
}