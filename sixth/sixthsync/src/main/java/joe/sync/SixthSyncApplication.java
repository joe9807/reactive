package joe.sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableCaching
@SpringBootApplication
@EnableMongoRepositories(basePackages = "joe.sync.repository.mongo")
@EnableJpaRepositories(basePackages = "joe.sync.repository.postgres")
public class SixthSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SixthSyncApplication.class, args);
	}

}
