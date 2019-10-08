package com.proch.project.ums.service;

import com.proch.project.ums.entity.Role;
import com.proch.project.ums.repository.RoleRepository;
import com.proch.project.ums.vo.RoleVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.ArrayList;
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

    @Transient
    public void saveRole(RoleVo.RoleFormVo roleFormVo) {
        Role role = new Role();        
        if (StringUtils.isNotBlank(roleFormVo.getIdStr())) {
        	role = roleRepository.findById(Long.valueOf(roleFormVo.getIdStr())).get();
		}
        
        role.setRoleName(roleFormVo.getRoleName());
        role.setDescription(roleFormVo.getDescription());
        roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
