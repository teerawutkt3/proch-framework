package com.proch.project.ums.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.proch.project.ums.entity.RoleAssignment;

public interface RoleAssignmentRepository extends CrudRepository<RoleAssignment, Long> {
	
	List<RoleAssignment> findByUserId(Long userId);
}
