package com.mv.project.ums.repositories;

public class UserRepositoryImpl implements UserRepositoryCustom{

	@Override
	public String filterBy(String role) {
		return role;
	}

	
}
