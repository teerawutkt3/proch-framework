package com.mv.project.ums.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mv.project.ums.entities.Role;
import com.mv.project.ums.repositories.UserRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.mv.project.ums.entities.User user = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username + ".");
        } else {

        	List<Role> roleList = user.getRoles();
//        	roleList.toArray();
//            List<Role> roleList = user.getRoles();
//            List<Role> roleList = new ArrayList<Role>();
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        User userDetail = new User(user.getUsername(), user.getPassword(), authorities); // "{noop}"+
        return userDetail;
    }

}
