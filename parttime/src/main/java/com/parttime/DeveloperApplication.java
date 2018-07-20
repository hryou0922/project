package com.parttime;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeveloperApplication {

	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			args = new String[1];
			args[0] = "--spring.profiles.active=local";
		}
		SpringApplication.run(DeveloperApplication.class, args);
	}
}
