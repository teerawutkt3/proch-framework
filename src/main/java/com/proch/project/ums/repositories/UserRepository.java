package com.proch.project.ums.repositories;

import com.proch.project.common.repositories.CommonJpaCrudRepository;
import com.proch.project.ums.entities.User;

public interface UserRepository extends CommonJpaCrudRepository<User, Long> {
	
	User findByUsername(String username);
}
