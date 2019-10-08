package com.proch.project.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proch.project.common.bean.ResponseData;
import com.proch.project.common.constant.ProjectConstant;
import com.proch.project.common.utils.MessageUtil;
import com.proch.project.common.utils.UserLoginUtils;
import com.proch.project.ums.entity.User;
import com.proch.project.ums.service.UserSerivce;
import com.proch.project.ums.vo.RegisterVo;
import com.proch.project.ums.vo.UserProfileVo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserSerivce userSerivce;

	@GetMapping("/403")
	public ResponseData<String> error403() {
		ResponseData<String> responseData = new ResponseData<String>();
		responseData.setData(null);
		MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
		return responseData;
	}
	@GetMapping("/profile")
	public ResponseData<UserProfileVo> profile() {
		ResponseData<UserProfileVo> responseData = new ResponseData<UserProfileVo>();

		try {
			UserProfileVo userProfile = new UserProfileVo();
			String username = UserLoginUtils.getCurrentUsername();
			List<String> roles = UserLoginUtils.getGrantedAuthorityList();

			userProfile.setUsername(username);
			userProfile.setRole(roles);

			responseData.setData(userProfile);
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessageFail(responseData, ProjectConstant.Error._403);
		}
		return responseData;
	}

	@GetMapping("/user-all")
	public ResponseData<List<User>> getUserAll() {
		ResponseData<List<User>> responseData = new ResponseData<>();

		try {
			responseData.setData(userSerivce.getUserAll());
			MessageUtil.setMessage(responseData, ProjectConstant.ResponseMessage.SUCCESS,
					ProjectConstant.ResponseStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
		}
		return responseData;
	}

	@PostMapping("/register")
	public ResponseData<User> register(@RequestBody RegisterVo registerVo) {
		ResponseData<User> responseData = new ResponseData<>();

		try {
			userSerivce.register(registerVo);
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
		}
		return responseData;
	}

	@PostMapping("/generate-user")
	public ResponseData<User> generateUser(@RequestBody RegisterVo formVo) {
		ResponseData<User> responseData = new ResponseData<>();

		try {
			responseData.setData(userSerivce.register(formVo));
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessageFail(responseData);
		}
		return responseData;
	}
	
	@DeleteMapping("/delete{id}")
	public ResponseData<User> deleteUser(@RequestParam Long id) {
		ResponseData<User> responseData = new ResponseData<>();
		
		try {
			userSerivce.deleteUser(id);
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessageFail(responseData);
		}
		return responseData;
	}

}