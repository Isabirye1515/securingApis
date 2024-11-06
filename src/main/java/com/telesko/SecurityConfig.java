package com.telesko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // In-memory authentication for testing purposes
        auth.inMemoryAuthentication()
            .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/login", "/api/**", "/js/**").authenticated()
        // Allow access to login page and static resources
                .antMatchers("/api/hello").hasRole("USER") // Require USER role for /api/hello
                .anyRequest().authenticated() // Require authentication for all other requests
                .and()
                .formLogin()
                .loginPage("/") // Specify your login page
                .loginProcessingUrl("/perform_login") 
                .defaultSuccessUrl("/api/hello")// Specify a custom URL for processing login
                .permitAll()
                .and()
            .logout()
                .permitAll(); // Allow logout for everyone
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}