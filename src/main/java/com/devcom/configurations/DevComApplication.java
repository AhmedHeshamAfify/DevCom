package com.devcom.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class DevComApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevComApplication.class, args);
	}

}
