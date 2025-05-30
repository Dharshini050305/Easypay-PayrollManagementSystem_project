package com.hexaware.easypay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.entities.User;
import com.hexaware.easypay.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public String addUser(User userInfo) {  // user registration
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }

}
