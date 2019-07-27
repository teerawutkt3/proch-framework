package com.proch.project.ums.service;

import com.proch.project.ums.entity.Role;
import com.proch.project.ums.repository.RoleRepository;
import com.proch.project.ums.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void insertRole(RoleVo roleVo) {

        Role role = new Role();
        role.setRoleName(roleVo.getRoleName());
        role.setDescription(roleVo.getDescription());
        System.out.printf("date :"+ role.getCreatedDate());
        roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
