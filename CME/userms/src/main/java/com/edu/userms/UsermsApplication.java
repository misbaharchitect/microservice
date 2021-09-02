package com.edu.userms;

import com.edu.userms.model.User;
import com.edu.userms.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class UsermsApplication {

	@Autowired
	private UserRepo repo;

	@GetMapping("/users")
	public List<User> getUsers() {
		return repo.findAll();
	}

	@PostMapping("/users")
	public User saveUser(@RequestBody User user) {
		User newUser = repo.save(user);
		return newUser;
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		Optional<User> user = repo.findById(userId);

		if(user.isPresent()) return ResponseEntity.ok(user.get());

		return ResponseEntity.notFound().build();
	}


	@GetMapping("/hello")
	public String getHello() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(UsermsApplication.class, args);
	}

}
