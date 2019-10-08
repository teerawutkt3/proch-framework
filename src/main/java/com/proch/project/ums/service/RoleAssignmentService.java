package com.proch.project.ums.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proch.project.ums.entity.Role;
import com.proch.project.ums.entity.User;
import com.proch.project.ums.repository.RoleRepository;
import com.proch.project.ums.repository.UserRepository;
import com.proch.project.ums.vo.RoleAssignmentVo;

@Service
public class RoleAssignmentService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	
	public User save(RoleAssignmentVo vo) {
		
		User user = userRepository.findById(vo.getUserId()).get();		
		List<Role> hasRole = new ArrayList<Role>();
		List<User> hasUser = new ArrayList<User>();
		
		for (Role role : user.getRoles()) {
			
			hasUser.clear();
			hasRole.clear();
			role.setUsers(hasUser);
		}
		user.setRoles(hasRole);
		user = userRepository.save(user);
		for (Long roleId : vo.getRoleIds()) {
			Role role = roleRepository.findById(roleId).get();
			
			hasRole.add(role);zvxcvzx
			hasUser.add(user);
			role.setUsers(hasUser);
			user.setRoles(hasRole);	
			user = userRepository.save(user);
		}
		
		return user;
	}
}
