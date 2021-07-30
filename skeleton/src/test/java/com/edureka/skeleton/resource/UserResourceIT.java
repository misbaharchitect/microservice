package com.edureka.skeleton.resource;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.edureka.skeleton.model.User;
import com.edureka.skeleton.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserResourceIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TestRestTemplate testRestTemplate; // triggers integration test
	
	@Test
	void getAllUsers() throws Exception {
		ResponseEntity<List<User>> response = testRestTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<User>>() {
		});
		
		MatcherAssert.assertThat(response.getStatusCode(), CoreMatchers.equalTo(HttpStatus.OK));
		List<User> usersList = response.getBody();
		assertNotNull(usersList);
		usersList.forEach(s-> System.out.println(s));
		
		
	}
	
	

}
