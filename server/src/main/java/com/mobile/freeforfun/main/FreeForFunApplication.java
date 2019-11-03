package com.mobile.freeforfun.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = "com.mobile.freeforfun")
@EnableJpaRepositories(basePackages = "com.mobile.freeforfun")
@EntityScan(basePackages = "com.mobile.freeforfun")
@EnableScheduling
public class FreeForFunApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreeForFunApplication.class, args);
	}
}
