package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
	

}
