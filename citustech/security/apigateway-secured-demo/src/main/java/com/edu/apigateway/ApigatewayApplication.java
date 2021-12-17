package com.edu.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;

@SpringBootApplication
public class ApigatewayApplication {

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		System.out.println("*****************");
//		System.out.println("*****************");
//		/*return builder.routes()
//				.route(p -> p
//						.path("/orders/**")
//						.filters(f -> f.addRequestHeader("Hello", "World"))
//						.uri("http://localhost:8082/orders"))
//				.build();*/
//		/*return builder.routes().route(p -> p.path("/**")
//				.filters(f -> {
//					if (f.get)
//					return f.addRequestHeader("Hello", "World");
//					}
//						).uri("no://op")).build();*/
//	}

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

}
