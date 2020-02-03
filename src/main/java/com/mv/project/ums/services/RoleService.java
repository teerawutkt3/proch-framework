package com.mv.project.ums.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mv.project.ums.entities.Role;
import com.mv.project.ums.repositories.RoleRepository;
import com.mv.project.ums.vo.RoleVo;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Optional<Role> opt = roleRepository.findById(Long.valueOf(roleFormVo.getIdStr()));
            if(opt.isPresent()){ role = opt.get();}
		}
        role.setRoleName(roleFormVo.getRoleName());
        role.setDescription(roleFormVo.getDescription());
        roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
