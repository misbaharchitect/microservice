package com.edu.resourceserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceserverDemoApplication {


	@GetMapping("/hello")
	public String getHello() {
		return "Hello World!";
	}



	public static void main(String[] args) {
		SpringApplication.run(ResourceserverDemoApplication.class, args);
	}

}
