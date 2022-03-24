package com.edu.securedms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails john = User.withUsername("john")
                .password("123")
                .roles("read", "hello")
                .build();
        UserDetails alice = User.withUsername("alice")
                .password("123")
                .roles("write", "hello")
                .build();

        UserDetails jane = User.withUsername("jane")
                .password("123")
                .roles("write", "welcome")
                .build();
        UserDetails alex = User.withUsername("alex")
                .password("123")
                .roles("read", "welcome")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(john);
        userDetailsManager.createUser(jane);
        userDetailsManager.createUser(alice);
        userDetailsManager.createUser(alex);
        return userDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.httpBasic();
        http.csrf().disable().authorizeRequests().anyRequest().authenticated();*/

        /*http.httpBasic();
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();*/

        /*http.httpBasic();
        http.csrf().disable().authorizeRequests()
                .mvcMatchers("/hello").hasRole("read");*/

        /*http.httpBasic();
        http.csrf().disable().authorizeRequests()
                .mvcMatchers("/hello").hasRole("read")
                .mvcMatchers("/welcome").hasRole("write");*/

        /*http.httpBasic();
        http.csrf().disable().authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/hello").hasAnyRole("read", "hello")
                .mvcMatchers(HttpMethod.POST, "/hello").hasAnyRole("write", "hello")
                .mvcMatchers("/welcome").hasRole("write");*/

        http.httpBasic();
        http.csrf().disable().authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/hello").access("hasRole('hello') and hasRole('read')")
                .mvcMatchers(HttpMethod.POST, "/hello").access("hasRole('hello') and hasRole('write')")
                .mvcMatchers(HttpMethod.GET, "/welcome").access("hasRole('welcome') and hasRole('read')")
                .mvcMatchers(HttpMethod.POST, "/welcome").access("hasRole('welcome') and hasRole('write')")
//                .anyRequest().denyAll()
                ;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
