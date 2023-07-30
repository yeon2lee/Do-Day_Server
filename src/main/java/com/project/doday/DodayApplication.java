package com.project.doday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DodayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DodayApplication.class, args);
	}

}
