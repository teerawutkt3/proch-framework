package com.mv.project.ums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mv.project.common.beans.ResponseData;
import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.utils.MessageUtil;
import com.mv.project.ums.entities.User;
import com.mv.project.ums.services.RoleAssignmentService;
import com.mv.project.ums.vo.RoleAssignmentVo;

import java.util.List;

@RestController
@RequestMapping("/api/role-assignment")
public class RoleAssignmentController {
	
	@Autowired
	private RoleAssignmentService roleAssignmentService;
	
	@PostMapping("/")
	public ResponseData<User> save(@RequestBody RoleAssignmentVo vo){
		ResponseData<User> responseData = new ResponseData<>();
		try {
			responseData.setData(roleAssignmentService.save(vo));			
			MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
		} catch (Exception e) {
			MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
		}
		return responseData;
	}

	// ==> Get role by user id
	@GetMapping("/findByUserId/{userId}")
	public ResponseData<List<Long>> findByUserId(@PathVariable Long userId) {
		ResponseData<List<Long>> responseData = new ResponseData<>();
		try {
			responseData.setData(roleAssignmentService.findByUserId(userId));
			MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Delete.SUCCESS);
		} catch (Exception e) {
			MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Delete.FAILED);
		}
		return responseData;
	}

}
