package com.proch.project.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proch.project.ums.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
