package com.proch.project.ums.repository;

import com.proch.project.common.repository.CommonJpaCrudRepository;
import com.proch.project.ums.entity.User;

public interface UserRepository extends CommonJpaCrudRepository<User, Long> {
	
	User findByUsername(String username);
}
