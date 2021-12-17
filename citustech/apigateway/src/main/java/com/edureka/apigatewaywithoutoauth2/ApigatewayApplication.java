package com.edureka.apigatewaywithoutoauth2;

import com.edureka.apigatewaywithoutoauth2.filters.AddRequestHeaderFilter;
import com.edureka.apigatewaywithoutoauth2.filters.PostFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public AddRequestHeaderFilter addRequestHeaderFilter() {
		return new AddRequestHeaderFilter();
	}

	@Bean
	public PostFilter postHeaderFilter() {
		return new PostFilter();
	}

}
