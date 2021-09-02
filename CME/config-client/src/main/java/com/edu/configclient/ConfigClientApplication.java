package com.edu.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class ConfigClientApplication {
	@Value("${greeting}")
	private String greeting;

	@GetMapping("/properties")
	public String greet() {
		return greeting;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
