package com.edu.lowercasems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class LowercasemsApplication {

	@Bean
	public Function<String, String> toLowercase() {
		return s -> { // input -> abcd(topic)
			System.out.println("uppercase receives: " + s);
			return s.toLowerCase(); // output -> mytopic123
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(LowercasemsApplication.class, args);
	}

}
