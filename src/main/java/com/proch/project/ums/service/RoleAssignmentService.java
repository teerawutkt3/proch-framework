package com.proch.project.ums.service;

import com.proch.project.ums.entity.Role;
import com.proch.project.ums.entity.User;
import com.proch.project.ums.repository.RoleRepository;
import com.proch.project.ums.repository.UserRepository;
import com.proch.project.ums.vo.RoleAssignmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleAssignmentService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;


	@Transactional
	public User save(RoleAssignmentVo vo) {
		User user = userRepository.findById(vo.getUserId()).get();

		List<Role> hasRole = new ArrayList<>();
		List<User> hasUser = new ArrayList<>();

		for (Role role : user.getRoles()) {
			role.getUsers().remove(user);
			user.setRoles(hasRole);
			roleRepository.save(role);
		}
		user = userRepository.save(user);

		user.setRoles(hasRole);
		hasUser.add(user);
		for (Long roleId : vo.getRoleIds()) {
			Role role = roleRepository.findById(roleId).get();
			hasRole.add(role);
			role.getUsers().add(user);
			user.setRoles(hasRole);
		}
		user = userRepository.save(user);
		return user;
	}
}
