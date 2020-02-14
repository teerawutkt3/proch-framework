package com.mv.project.ums.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mv.project.ums.entities.Role;
import com.mv.project.ums.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getRole() {
		Iterable<Role> role = roleRepository.findAll();

		List<Role> roleList = new ArrayList<>();
		role.forEach(roleList::add);
		return roleList;
	}

	public Role findById(long id) {
		Optional<Role> result = roleRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Transient
	public void saveRole(Role role) {		
		roleRepository.save(role);
	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}
