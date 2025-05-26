package com.hexaware.easypay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByUserName(String username);

}
