package com.example.tusker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.tusker")
@EnableJpaRepositories(basePackages = "com.example.tusker.repositories")
public class TuskerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuskerApplication.class, args);
	}

}
