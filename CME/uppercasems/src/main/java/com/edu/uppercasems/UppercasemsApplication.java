package com.edu.uppercasems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class UppercasemsApplication {

	@Bean
	public Function<String, String> toUppercase() {
		return s -> { // input -> mytopic
			System.out.println("uppercase receives: " + s);
			return s.toUpperCase(); // output -> abcd(topic)
		};
		// return s-> s.toUpperCase();
	}

	public static void main(String[] args) {
		SpringApplication.run(UppercasemsApplication.class, args);
	}

}
