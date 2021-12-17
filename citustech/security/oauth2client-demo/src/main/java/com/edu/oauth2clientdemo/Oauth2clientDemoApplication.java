package com.edu.oauth2clientdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class Oauth2clientDemoApplication {

	@Autowired
	private WebClient webClient;

	@GetMapping("/hello")
	public Mono getHello() {
		return webClient
				.get()
//				.uri("http://localhost:8083/secure/hello")
//				.uri("http://localhost:8087/secure/hello")
				.uri("http://localhost:8087/hello")
				.retrieve()
//				.bodyToMono(Object.class) // Mono(Single), Flux(Many)
				.bodyToMono(String.class) // Mono(Single), Flux(Many)
				.map(s-> {
					System.out.println("************");
					System.out.println("************");
					System.out.println("************");
					System.out.println("************");
					return s;
				});
//				.then()
//				.block();
	}


	public static void main(String[] args) {
		SpringApplication.run(Oauth2clientDemoApplication.class, args);
	}

}
