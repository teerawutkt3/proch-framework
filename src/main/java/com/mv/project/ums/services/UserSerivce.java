package com.mv.project.ums.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mv.project.ums.entities.User;
import com.mv.project.ums.repositories.UserRepository;
import com.mv.project.ums.vo.RegisterVo;

@Service
public class UserSerivce {

    @Autowired
    private UserRepository userRepository;

    public User register(RegisterVo registerVo) {
        User user = new User();
        user.setUsername(registerVo.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(registerVo.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUserAll() {

        Iterable<User> users = userRepository.findAll();               
        
        ArrayList<User> userList = new ArrayList<User>(); 
       
        if(!users.toString().isEmpty())
        	userList = Lists.newArrayList(users);

        return userList;
    }
    
    public void deleteUser(Long userId) {
    	userRepository.deleteById(userId);
    }
}
