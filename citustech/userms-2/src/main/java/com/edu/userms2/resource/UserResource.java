package com.edu.userms2.resource;

import com.edu.userms2.OrdermsFeignClient;
import com.edu.userms2.model.User;
import com.edu.userms2.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    private final UserRepository userRepo;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final OrdermsFeignClient feignClient;

    public UserResource(UserRepository empRepo, RestTemplate restTemplate, WebClient.Builder webClientBuilder, OrdermsFeignClient feignClient) {
        this.userRepo = empRepo;
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.feignClient = feignClient;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/users/orders")
    @HystrixCommand(fallbackMethod = "getOrdersFromFallback", commandProperties =
            {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
                    // controls the amount of consecutive calls that must occur within a 10-second window
                    // before Hystrix will consider tripping the circuit breaker for the call
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    // the percentage of calls that must fail (due to timeouts, an exception being thrown,
                    // or a HTTP 500 being returned) after the circuitBreaker.requestVolumeThreshold value
                    // has been passed before the circuit breaker is tripped.
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "20000"),
                    // the amount of time Hystrix will sleep once the circuit breaker is tripped before
                    // Hystrix will allow another call through to see if the service is healthy again.
                    @HystrixProperty(name = "circuitBreaker.sleepWindowingMilliseconds", value = "20000")
            },
            threadPoolKey = "getAllUsersThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public Object getAllOrdersRestTemplate() {
        System.out.println("RestTemplate");
        // orderms call

//        Object forObject = restTemplate.getForObject("http://localhost:8082/orders", Object.class);
        Object forObject = restTemplate.getForObject("http://orderms/orders", Object.class);
        /*String order = "{\n" +
                "  \"name\": \"blackberry\",\n" +
                "  \"serial\": 9999\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(order, headers);
        Object forObject = restTemplate.postForObject("http://localhost:8082/orders", request,  Object.class);
        */
        return forObject;
    }

    @GetMapping("/users/orders-feignclient")
    @HystrixCommand(fallbackMethod = "getOrdersFromFallback")
    public Object getAllOrdersFeignClient() {
        System.out.println("Feign Client");
        return feignClient.getOrders();
    }

    @GetMapping("/users/orders-webclient")
    @HystrixCommand(fallbackMethod = "getOrdersFromFallback")
    public Object getAllOrdersWebClient() {
        System.out.println("Web Client");
        return webClientBuilder.build().get().uri("http://orderms/orders")
                .retrieve().bodyToMono(String.class).block();
    }

    private Object getOrdersFromFallback() {
        return Arrays.asList("A", "B", "C");
    }

    @GetMapping("/users/{empId}")
    public User getSingleUsers(@PathVariable Long empId) {
        Optional<User> emp = userRepo.findById(empId);

        return emp.orElse(new User());
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User emp) {
        return userRepo.save(emp);
    }

    @DeleteMapping("/users/{empId}")
    public void deleteUser(Long empId) {
        userRepo.deleteById(empId);
    }










}
