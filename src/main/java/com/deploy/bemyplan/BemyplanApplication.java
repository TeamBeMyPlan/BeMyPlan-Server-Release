package com.deploy.bemyplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BemyplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BemyplanApplication.class, args);
	}

}