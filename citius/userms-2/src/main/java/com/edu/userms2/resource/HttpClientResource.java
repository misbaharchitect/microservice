package com.edu.userms2.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class HttpClientResource {

    @Autowired
    private WebClient.Builder webBuilder;

    @GetMapping("/orders-web")
    public Object getOrdersThroughWebClient() {
//        Object forObject = restTemplate.getForObject("http://orderms/orders", Object.class);
        System.out.println("Web Client is called");
        return webBuilder.build()
                .get()
                .uri("http://orderms/orders")
                .retrieve()
                .bodyToMono(Object.class) // Mono(Single), Flux(Many)
                .block();
    }
}
