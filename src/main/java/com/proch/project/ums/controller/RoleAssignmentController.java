package com.proch.project.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proch.project.common.bean.ResponseData;
import com.proch.project.common.constant.ProjectConstant;
import com.proch.project.common.utils.MessageUtil;
import com.proch.project.ums.entity.User;
import com.proch.project.ums.service.RoleAssignmentService;
import com.proch.project.ums.vo.RoleAssignmentVo;

@RestController
@RequestMapping("/api/role-assignment")
public class RoleAssignmentController {
	
	@Autowired
	private RoleAssignmentService roleAssignmentService;
	
	@PostMapping("/save")
	public ResponseData<User> save(@RequestBody RoleAssignmentVo vo){
		ResponseData<User> responseData = new ResponseData<User>();
		try {
			responseData.setData(roleAssignmentService.save(vo));			
			MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
		} catch (Exception e) {
			MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
		}
		return responseData;
	}
}
