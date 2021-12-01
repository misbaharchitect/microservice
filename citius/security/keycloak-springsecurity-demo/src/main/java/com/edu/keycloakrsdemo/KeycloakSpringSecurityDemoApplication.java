package com.edu.keycloakrsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KeycloakSpringSecurityDemoApplication {

	@GetMapping("/hello")
	public String getHello() {
		return "Hello World!";
	}

	@GetMapping("/customers/hello")
	public String getCustomerHello() {
		return "Hello Customer!";
	}

	@GetMapping("/managers/hello")
	public String getManagerHello() {
		return "Hello Manager!";
	}

	@GetMapping("/employees/hello")
	public String getEmployeesHello() {
		return "Hello Employees!";
	}

	public static void main(String[] args) {
		SpringApplication.run(KeycloakSpringSecurityDemoApplication.class, args);
	}

}
