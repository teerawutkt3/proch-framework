package com.mv.project.ums.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mv.project.common.beans.ResponseData;
import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.utils.MessageUtil;
import com.mv.project.common.utils.UserLoginUtils;
import com.mv.project.ums.entities.User;
import com.mv.project.ums.services.UserSerivce;
import com.mv.project.ums.vo.RegisterVo;
import com.mv.project.ums.vo.UserProfileVo;
import com.mv.project.ums.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public	class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserSerivce userSerivce;

	@GetMapping("/403")
	public ResponseData<String> error403() {
		ResponseData<String> responseData = new ResponseData<String>();
		responseData.setData(null);
		MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
		return responseData;
	}
	@GetMapping("/test")
	public ResponseData<String> TestHttp() {
		ResponseData<String> responseData = new ResponseData<String>();
		responseData.setData("test");
		MessageUtil.setMessageSuccess(responseData);
		return responseData;
	}
	@PostMapping("/testPost")
	public ResponseData<RegisterVo> TestHttpPost(@RequestBody RegisterVo request) {
		ResponseData<RegisterVo> responseData = new ResponseData<RegisterVo>();
		responseData.setData(request);
		MessageUtil.setMessageSuccess(responseData);
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
			userProfile.setRoles(roles);

			responseData.setData(userProfile);
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessageFail(responseData, ProjectConstant.Error._403);
		}
		return responseData;
	}

	@GetMapping("/")
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

	@GetMapping("/{id}")
	public ResponseData<User> findByUserId(@PathVariable long id) {
		ResponseData<User> responseData = new ResponseData<>();
		try {
			responseData.setData(userSerivce.findByUserId(id));
			MessageUtil.setMessage(responseData, ProjectConstant.ResponseMessage.SUCCESS,
					ProjectConstant.ResponseStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
		}
		return responseData;
	}

	@PostMapping("/")
	public ResponseData<UserVo> register(@RequestBody UserVo userVo) {
		ResponseData<UserVo> responseData = new ResponseData<>();
		try {
			logger.info("Method register ==> {}", userVo.toString());
			responseData.setData(userSerivce.save(userVo));
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			logger.error("Method register err");
			e.printStackTrace();
			MessageUtil.setMessage(responseData, e.getMessage(), ProjectConstant.ResponseStatus.FAILED);
		}
		return responseData;
	}

	@PostMapping("/generate-user")
	public ResponseData<UserVo> registerNotAuthen(@RequestBody UserVo userVo) {
		ResponseData<UserVo> responseData = new ResponseData<>();
		try {
			responseData.setData(userSerivce.save(userVo));
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
		}
		return responseData;
	}

	@GetMapping("/generate-password{password}")
	public ResponseData<String> generatePassword(@RequestParam String password) {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			responseData.setData(new BCryptPasswordEncoder().encode(password));
			MessageUtil.setMessageSuccess(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.setMessageFail(responseData);
		}
		return responseData;
	}
	
	@DeleteMapping("/{id}")
	public ResponseData<User> deleteUser(@PathVariable Long id) {
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