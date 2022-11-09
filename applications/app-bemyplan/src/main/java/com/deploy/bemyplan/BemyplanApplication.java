package com.deploy.bemyplan;

import com.zkdlu.apiresponsespringbootstarter.autoconfig.EnableResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableResponse
@EnableJpaAuditing
@SpringBootApplication
public class BemyplanApplication {

	private static final String APPLICATION_LOCATIONS = "spring.config.location=" +
			"classpath:application.yml," +
			"classpath:aws.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(BemyplanApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}