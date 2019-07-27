package com.proch.project.ums.repository;

import com.proch.project.common.repository.CommonJpaCrudRepository;
import org.springframework.data.repository.CrudRepository;

import com.proch.project.ums.entity.Role;

public interface RoleRepository extends CommonJpaCrudRepository<Role, Long> {

}
