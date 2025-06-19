package com.hexaware.easypay.filter;
/**
 * Filter that intercepts incoming HTTP requests once per request to validate JWT tokens.
* @author Dharshini 
* @version 1.0
* */

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hexaware.easypay.config.UserDService;
import com.hexaware.easypay.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserDService userDetailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	    System.out.println("JwtAuthFilter is processing the request: " + request.getRequestURI());

		  String authHeader = request.getHeader("Authorization");
		  System.out.println("Authorization header: " + authHeader);
	        String token = null;
	        String username = null;

	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            username = jwtService.extractUserName(token);
	            System.out.println("Extracted username: " + username);
	        }

	        System.out.println("Already authenticated? " + SecurityContextHolder.getContext().getAuthentication());

	        
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            System.out.println("Validating token for user: " + userDetails.getUsername());
	            if (jwtService.validateToken(token, userDetails)) {
	                System.out.println("Token is valid!");
	                System.out.println("Authenticated user: " + userDetails.getUsername());
	                System.out.println("Authorities: " + userDetails.getAuthorities());
	               
	                @SuppressWarnings("unused")
					Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
	            
	                //it actually creates a Spring Security Authentication object that represents a fully authenticated user.
	                UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());

	            	 System.out.println("Authenticated user: " + userDetails.getUsername());
	            	  System.out.println("Authorities: " + userDetails.getAuthorities());
    
	            	  //This adds extra info about the request, such as:IP address,Session ID
	            	authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            	
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	    
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
	    String path = request.getServletPath();
	    return path.startsWith("/v3/api-docs")
	            || path.startsWith("/swagger-ui")
	            || path.equals("/swagger-ui.html")
	            || path.startsWith("/users"); // or your actual public path
	}
}