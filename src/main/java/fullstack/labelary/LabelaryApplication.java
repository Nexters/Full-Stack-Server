package fullstack.labelary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LabelaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabelaryApplication.class, args);
	}

}
