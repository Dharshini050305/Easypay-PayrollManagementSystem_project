package com.hexaware.easypay.controller;
/**
 * REST controller for user-related operations in the Payroll Management System.
 * * 
 * @author Dharshini
 * @version 1.0
 * */
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.AuthRequest;
import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.service.JwtService;
import com.hexaware.easypay.service.UserService;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService service;

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/registration/new")
	public String addNewUser(@RequestBody User userInfo) {
		return service.addUser(userInfo);
	}

	@PostMapping("/login/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		String token = null;

		if (authentication.isAuthenticated()) {

		

			token = jwtService.generateToken(authRequest.getUserName());

			logger.info("Token : " + token);

		} else {

			logger.info("invalid");

			throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");

		}

		return token;

	}
}
