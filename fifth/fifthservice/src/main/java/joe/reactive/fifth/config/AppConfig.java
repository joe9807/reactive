package joe.reactive.fifth.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "fifth")
public class AppConfig {
    private Next next;
    private String bootstrapServers;
    private String topic;
    private String groupId;

    @Data
    public static class Next{
        private String asyncUrl;
        private String syncUrl;
    }
}
