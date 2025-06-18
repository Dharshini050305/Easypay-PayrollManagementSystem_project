package com.hexaware.easypay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.repository.UserRepo;

@Service
public class UserService {
	@Autowired
    private UserRepo repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public String addUser(User userInfo) {  // user registration
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }

}
