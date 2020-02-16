package com.mv.project.ums.services;

import com.mv.project.ums.entities.Role;
import com.mv.project.ums.entities.User;
import com.mv.project.ums.entities.UserRole;
import com.mv.project.ums.repositories.RoleRepository;
import com.mv.project.ums.repositories.UserRepository;
import com.mv.project.ums.repositories.UserRoleRepository;
import com.mv.project.ums.vo.RoleAssignmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleAssignmentService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;


	@Transactional
	public User save(RoleAssignmentVo vo) {
		User user = userRepository.findById(vo.getUserId()).get();
		List<UserRole> userRoles = new ArrayList<>();
		for (long roleId: vo.getRoleIds()) {
			Optional<Role> roleOptional = roleRepository.findById(roleId);
			if(roleOptional.isPresent()){
				UserRole userrole = new UserRole();
				userrole.setUser(user);
				userrole.setRole(roleOptional.get());
				userRoles.add(userrole);
			}
			userRoleRepository.saveAll(userRoles);
		}
		return user;
	}

	public List<Long> findByUserId(long userId){
		List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
		List<Long> roleIds = new ArrayList<>();
		userRoles.forEach(role->{
			roleIds.add(role.getRole().getId());
		});
		return roleIds;
	}
}
