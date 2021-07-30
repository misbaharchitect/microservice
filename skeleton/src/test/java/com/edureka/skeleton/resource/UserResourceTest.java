package com.edureka.skeleton.resource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.edureka.skeleton.repository.UserRepository;
import com.edureka.skeleton.resource.UserResource;

@WebMvcTest(UserResource.class)
class UserResourceTest {
	
	@Autowired
	private MockMvc mockmvc; // similar to RestTemplate
	@MockBean
	private UserRepository repository;
	@MockBean
	private RestTemplate restTemplate;
	
	
	@Test
	void test_users() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
		mockmvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				// assertions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("[]"));
		
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}
	
	
	
	
	
	

	@Test
	void test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
		mockmvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				// assertions
				.andExpect(MockMvcResultMatchers.status().isOk());
//				.andExpect(MockMvcResultMatchers.content().equals("Hello World"));
	}

}
