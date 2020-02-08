package com.mv.project.ums.repositories;

import java.util.List;

import com.mv.project.common.repositories.CommonJpaCrudRepository;
import com.mv.project.ums.entities.User;

public interface UserRepository extends CommonJpaCrudRepository<User, Long> {
	
	User findByUsername(String username);

	List<User> findAllByOrderByCreatedDateDesc();
}
