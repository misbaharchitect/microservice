package com.edu.securedms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecuredmsApplication {

	/**
	 * Read Access Only but not by Write access
	 * @return
	 */
	@GetMapping("/hello")
	public String getHello() {
		return "Get Hello World!";
	}

	/**
	 * Write Access Only but not by Read access
	 * @return
	 */
	@PostMapping("/hello")
	public String postHello() {
		return "Post Hello World!";
	}

	/**
	 * Read Access Only but not by Write access
	 * @return
	 */
	@GetMapping("/welcome")
	public String getWelcome() {
		return "Get Welcome World!";
	}

	/**
	 * Write Access Only but not by Read access
	 * @return
	 */
	@PostMapping("/welcome")
	public String postWelcome() {
		return "Post Welcome World!";
	}

	/**
	 * Method Level Security
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_read')")
	@GetMapping("/secured")
	public String getSecured() {
		System.out.println("Get Secured Called");
		return "Get Secured";
	}

	@PostAuthorize("hasRole('ROLE_write')")
	@PostMapping("/secured")
	public String postSecured() {
		System.out.println("Post Secured Called");
		return "Post Secured";
	}

	public static void main(String[] args) {
		SpringApplication.run(SecuredmsApplication.class, args);
	}

}
