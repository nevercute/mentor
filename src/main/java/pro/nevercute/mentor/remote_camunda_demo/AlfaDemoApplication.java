package pro.nevercute.mentor.remote_camunda_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ConfigurationPropertiesScan
public class AlfaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlfaDemoApplication.class, args);
	}

}
