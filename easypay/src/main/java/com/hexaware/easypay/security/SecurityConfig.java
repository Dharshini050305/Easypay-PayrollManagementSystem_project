package com.hexaware.easypay.security;
/**
 * Defines the security filter chain for HTTP requests.
 * @author Dharshini
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.easypay.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JwtAuthFilter authFilter;
	@Autowired
	private UserDService userDetailsService;

	@Bean
	SecurityFilterChain   securityFilterChain(HttpSecurity http) throws Exception {
		
		
		 return http.cors().and().csrf().disable()
			        .authorizeHttpRequests()
			        .requestMatchers(HttpMethod.POST, "/users/login/authenticate").permitAll()
			        .requestMatchers(HttpMethod.POST, "/users/registration/new").permitAll()
			        .requestMatchers("/api/admin/**").permitAll()
			        .requestMatchers("/api/manager/**").permitAll()
			        .requestMatchers("/api/employee/**").permitAll()
			        .requestMatchers("/api/payroll/**").permitAll()
			        .anyRequest().authenticated()
			        .and()
			        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			        .authenticationProvider(authenticationProvider())
			        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
			        .build();
			}

		    @Bean    
		    public PasswordEncoder passwordEncoder() {          
		        return new BCryptPasswordEncoder();
		    }

    @Bean
  public  AuthenticationProvider authenticationProvider(){
		        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		        authenticationProvider.setUserDetailsService(userDetailsService);
		        authenticationProvider.setPasswordEncoder(passwordEncoder());
		        return authenticationProvider;
		    }
		    
		    
		    @Bean
		    //AuthenticationManager internally calls authenticationProvider()
		    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		    	
		    	return config.getAuthenticationManager();
		    	
		    }
		    
}