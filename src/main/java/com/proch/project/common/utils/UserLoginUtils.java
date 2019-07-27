package com.proch.project.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserLoginUtils {
	private static final Logger logger = LoggerFactory.getLogger(UserLoginUtils.class);

	private static Authentication getUserLogin(){
		Authentication auth = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {

			auth = SecurityContextHolder.getContext().getAuthentication();
		}
		return auth;
	}

	public static String getCurrentUsername() {
		if (getUserLogin() != null) return getUserLogin().getName();
		else return "test";
	}
	
	public static List<String> getGrantedAuthorityList() {
		List<String> authorityList = null;
		if (getUserLogin() != null){
			Collection<? extends GrantedAuthority> grantedAuthorityList = getUserLogin().getAuthorities();

			authorityList = new ArrayList<>();
			for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
				authorityList.add(grantedAuthority.getAuthority());
			}
		} else{
			new ArrayList<>();
		}
		
		return authorityList;
	}
	
}
