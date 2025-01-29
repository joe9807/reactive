package joe.reactive.first.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "first")
public class AppConfig {
    private Next next;

    @Data
    public static class Next{
        private String asyncUrl;
        private String syncUrl;
    }
}
