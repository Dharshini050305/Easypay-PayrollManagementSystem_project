package com.hexaware.easypay.config;
/**
 * class that defines userdetails by implementing UserDetails
 * @author dharshini
 * @version 1.0
 */
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.easypay.entity.User;

public class UserD implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private String name;
	    private String password;
	    private List<GrantedAuthority> authorities;

	    public UserD(User userd) {
	        name = userd.getUserName();
	        password = userd.getPassword();
	        authorities = List.of(new SimpleGrantedAuthority(userd.getRole().name()));
	    }


	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return authorities;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public String getUsername() {
	        return name;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}

