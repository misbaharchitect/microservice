package com.edu.lowercasems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;
import java.util.function.Function;

@SpringBootApplication
public class LowercasemsApplication {

	@Bean
	public Function<String, String> toLowercase() {
		return s -> {
			System.out.println("received: " + s);
			return s.toLowerCase();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LowercasemsApplication.class, args);
	}

}
