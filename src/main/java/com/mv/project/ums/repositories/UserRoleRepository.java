package com.mv.project.ums.repositories;

import com.mv.project.common.repositories.CommonJpaCrudRepository;
import com.mv.project.ums.entities.UserRole;

import java.util.List;

public interface UserRoleRepository extends CommonJpaCrudRepository<UserRole, Long> {
    List<UserRole> findByUserId(long userId);
    List<UserRole> findByRoleId(long roleId);
    void deleteByUserId(long userId);
}
