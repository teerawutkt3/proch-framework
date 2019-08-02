package com.proch.project.ums.service;

import com.proch.project.ums.entity.User;
import com.proch.project.ums.repository.UserRepository;
import com.proch.project.ums.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        return userList;
    }
}
