package com.edureka.skeleton.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.edureka.skeleton.model.User;
import com.edureka.skeleton.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class UserResource {
	private static final Logger LOGGGER = LoggerFactory.getLogger(UserResource.class);
	private final UserRepository repo;
	private final RestTemplate restTemplate;
	
	public UserResource(UserRepository repo, RestTemplate restTemplate) {
		this.repo = repo;
		this.restTemplate = restTemplate;
	}
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello World";
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	@GetMapping("/users/{userId}/orders")
	@HystrixCommand(fallbackMethod = "getUsersFromFallback", commandProperties =
		{@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")},
		//Bulkhead
		threadPoolKey = "getAllUsersThreadPool",
		threadPoolProperties = {
		@HystrixProperty(name = "coreSize", value = "30"),
		@HystrixProperty(name = "maxQueueSize", value = "10")
		})
	public Object getUsersOrders(@PathVariable Long userId) {
		Object orders = restTemplate.getForObject("http://orderms/orders/search/findByUserId?" 
									+ "userId=" + userId
									, Object.class);
		return orders;
	}
	
	public Object getUsersFromFallback(Long userId) {
		return "This is coming from Fallback";
	}
	

}
