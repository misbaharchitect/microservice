package com.edu.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Order(1)
@Component
public class Prefilter implements GlobalFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        System.out.println("****************");
        LOGGER.info("$$$$$$$$$$$$$$$$$$");
        exchange.mutate().request(
                exchange.getRequest().mutate().header("startTime", Instant.now().toString()).build()
        ).build();
        LOGGER.info("$$$$$$$$$$$$$$$$$$");
        return chain.filter(exchange);
    }
}
