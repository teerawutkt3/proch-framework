package com.proch.project.ums.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileVo {

	private String username;
	private List<String> role;
	private String token;

}
