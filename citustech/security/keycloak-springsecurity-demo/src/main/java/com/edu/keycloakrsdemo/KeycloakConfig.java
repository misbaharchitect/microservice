package com.edu.keycloakrsdemo;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * @KeycloakConfiguration = @EnableWebSecurity + @Configuration
 */
@KeycloakConfiguration
public class KeycloakConfig extends KeycloakWebSecurityConfigurerAdapter {
    /**
     * The application that we are building, in Keycloak terms, is a public application with user interaction.
     * In this scenario, the recommended session authentication strategy is RegisterSessionAuthenticationStrategy,
     * which registers a user session after successful authentication.
     * When securing a service-to-service application, instead, we would use a NullAuthenticatedSessionStrategy.
     * @return
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        /*http
                .authorizeRequests()
//                .antMatchers("/customers").hasAnyRole("user_role", "write_role")
                .antMatchers("/customers").hasAnyRole("write_role")
                .antMatchers("/managers").hasRole("write_role")
                .antMatchers("/employees").hasRole("user_role")
//                .anyRequest().permitAll();
                .anyRequest().authenticated();*/

        /*http.authorizeRequests()
                .antMatchers("/customers/**").authenticated()
                .antMatchers("/managers/**").authenticated()
                .anyRequest().permitAll()
        ;*/

        http.requestMatchers()
                .antMatchers("/customers/**", "/managers/**")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/customers/**").hasRole("user_role")
                .antMatchers(HttpMethod.GET, "/managers/**").hasRole("write_role")
                .antMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("user_role", "write_role")
//                .access("#oauth2.hasScope('read')")
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setPrefix("ROLE_"); // ROLE_user_role? check?

        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * Use below bean to overcome the error:
     * Unable to locate Keycloak configuration file: keycloak.json
     * @return
     */
    /*@Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }*/
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
