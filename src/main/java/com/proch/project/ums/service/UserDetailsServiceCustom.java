package com.proch.project.ums.service;

import com.proch.project.ums.entity.Role;
import com.proch.project.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.proch.project.ums.entity.User user = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username + ".");
        } else {

            List<Role> roleList = user.getRoles();
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        User userDetail = new User(user.getUsername(), user.getPassword(), authorities); // "{noop}"+
        return userDetail;
    }

}
