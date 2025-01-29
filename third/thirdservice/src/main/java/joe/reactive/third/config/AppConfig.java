package joe.reactive.third.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "third")
public class AppConfig {
    private Next next;
    private String bootstrapServers;
    private String callbackUrl;

    @Data
    public static class Next{
        private String topic;
        private String syncUrl;
    }
}
