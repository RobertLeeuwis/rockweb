package com.example.rockweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RockwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockwebApplication.class, args);
	}

}
