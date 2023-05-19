package com.hry.project.capcut;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapcutApplication {

	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			args = new String[1];
			args[0] = "--spring.profiles.active=dev";
		}
		SpringApplication.run(CapcutApplication.class, args);
	}
}
