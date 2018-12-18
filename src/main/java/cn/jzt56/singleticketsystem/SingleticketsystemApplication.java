package cn.jzt56.singleticketsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SingleticketsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleticketsystemApplication.class, args);
	}
}
