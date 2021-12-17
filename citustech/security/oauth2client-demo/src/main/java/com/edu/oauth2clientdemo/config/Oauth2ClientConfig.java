package com.edu.oauth2clientdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFluxSecurity
public class Oauth2ClientConfig  {
    public static final String TEST_REGISTRATION_ID = "test-client";

    @Bean
    @Primary
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().permitAll();
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//                .decoder(myCustomDecoder());
        return http.build();
    }

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        var clientRegistration = ClientRegistration.withRegistrationId(TEST_REGISTRATION_ID)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .clientId("candy")
                .clientSecret("123")
                .tokenUri("http://localhost:9000/oauth/token")
                .build();
        return new InMemoryReactiveClientRegistrationRepository(clientRegistration);
    }

    @Bean
    public WebClient testWebClient(ReactiveClientRegistrationRepository clientRegistrationRepo) {

        var oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepo,  new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
        oauth.setDefaultClientRegistrationId(TEST_REGISTRATION_ID);

        return WebClient.builder()
//                .baseUrl("http://localhost:8083/secure/hello")
                .filter(oauth)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
