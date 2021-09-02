package com.edu.usermsclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@RestController
@EnableHystrix
public class UsermsClientApplication {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/users-client")
	@HystrixCommand(fallbackMethod = "getUsersFromFallback")
	public Object getUsersFromServer() {
//		return restTemplate.getForObject("http://localhost:8080/users", Object.class);
		return restTemplate.getForObject("http://userms/users", Object.class);
	}

	private Object getUsersFromFallback() {
		return Arrays.asList("1", "2");
	}

	@PostMapping("/users-client")
	public ResponseEntity saveUsersFromServer(@RequestBody User user) throws URISyntaxException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<User> userHttpEntity = new HttpEntity<>(user, httpHeaders);
//		User user1 = restTemplate.postForObject("http://localhost:8080/users",
		User user1 = restTemplate.postForObject("http://userms/users",
				userHttpEntity, User.class);

		return ResponseEntity.created(new URI(user1.getId().toString())).body(user1);
	}

	public static void main(String[] args) {
		SpringApplication.run(UsermsClientApplication.class, args);
	}

}
