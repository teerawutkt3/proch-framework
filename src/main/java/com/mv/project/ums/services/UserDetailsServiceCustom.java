package com.mv.project.ums.services;

import com.mv.project.ums.entities.UserRole;
import com.mv.project.ums.repositories.UserRepository;
import com.mv.project.ums.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.mv.project.ums.entities.User user = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username + ".");
        } else {
            List<UserRole> usersRoles = userRoleRepository.findByUserId(user.getId());
            usersRoles.forEach(userRole -> {
                authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRoleCode()));
            });
        }
        User userDetail = new User(user.getUsername(), user.getPassword(), authorities); // "{noop}"+
        return userDetail;
    }

}
