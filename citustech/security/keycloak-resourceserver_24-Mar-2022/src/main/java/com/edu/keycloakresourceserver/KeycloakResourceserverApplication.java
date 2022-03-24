package com.edu.keycloakresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KeycloakResourceserverApplication {

	@GetMapping("/managers/hello")
	public String getManagersHello() {
		return "Hello World!";
	}

	@GetMapping("/employees/hello")
	public String getEmployeesHello() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(KeycloakResourceserverApplication.class, args);
	}

}
