package com.edu.uppercasems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class UppercasemsApplication {

	@Bean
	public Function<String, String> toUppercase() {
		return s -> {
			System.out.println("received: " + s);
			return s.toUpperCase();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(UppercasemsApplication.class, args);
	}

}
