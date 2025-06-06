package joe.sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SixthSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SixthSyncApplication.class, args);
	}

}
