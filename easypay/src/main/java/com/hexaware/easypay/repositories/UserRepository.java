package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
