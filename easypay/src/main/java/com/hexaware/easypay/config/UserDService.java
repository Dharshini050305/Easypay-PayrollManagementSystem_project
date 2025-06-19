package com.hexaware.easypay.config;
/**
 * class that defines UserDService by implementing UserDetailsService
 * @author Dharshini
 * @version 1.0
 */
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.repository.UserRepo;
@Service
public class UserDService implements UserDetailsService{
	@Autowired
    private UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findByUserName(username);
        
        return userInfo.map(UserD::new) 
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }


}
