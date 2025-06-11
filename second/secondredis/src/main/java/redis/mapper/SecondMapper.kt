package redis.mapper

import joe.model.SecondDto
import joe.reactive.dto.ThirdDto
import org.springframework.stereotype.Component

@Component
class SecondMapper {
    fun map(secondDto: SecondDto): ThirdDto {
        return ThirdDto.builder()
            .field111(secondDto.field11)
            .field222(secondDto.field22)
            .field333(secondDto.field33)
            .field444(secondDto.field44)
            .build()
    }
}