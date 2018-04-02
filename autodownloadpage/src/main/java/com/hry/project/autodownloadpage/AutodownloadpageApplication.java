package com.hry.project.autodownloadpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath:config/comic/spring_*.xml")
public class AutodownloadpageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutodownloadpageApplication.class, args);
	}
}
