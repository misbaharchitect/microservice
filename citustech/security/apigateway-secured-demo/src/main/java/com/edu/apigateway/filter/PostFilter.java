package com.edu.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Configuration
public class PostFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostFilter.class);
    @Bean
    public GlobalFilter globalFilter() {
        System.out.println("$$$$$$$$$$$$$$$$$$");
        LOGGER.info("$$$$$$$$$$$$$$$$$$");
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(
                            () -> {
                                System.out.println("$$$$$$$$$$$$$$$$$$");
                                LOGGER.info("$$$$$$$$$$$$$$$$$$");
                                HttpHeaders headers = exchange.getRequest().getHeaders();
                                List<String> startTime = headers.get("startTime");
                                Optional<String> startTimeHeader = startTime.stream().findAny();
                                Instant startTimeInstant = Instant.parse(startTimeHeader.get());
                                long between = ChronoUnit.MILLIS.between(startTimeInstant, Instant.now());
                                LOGGER.info("$$$$$$$$$$$$$$$$$$");

                                System.out.println("Total time taken: " + between);
                                LOGGER.info("Total time taken: {}", between);

                            }

                    ));
        };
    }
}
