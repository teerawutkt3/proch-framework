package com.proch.project.ums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proch.project.common.beans.ResponseData;
import com.proch.project.common.constants.ProjectConstant;
import com.proch.project.common.utils.MessageUtil;
import com.proch.project.ums.entities.User;
import com.proch.project.ums.services.RoleAssignmentService;
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
