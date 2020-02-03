package com.mv.project.ums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mv.project.common.beans.ResponseData;
import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.utils.MessageUtil;
import com.mv.project.ums.entities.Role;
import com.mv.project.ums.services.RoleService;
import com.mv.project.ums.vo.RoleVo;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //==> Get Role
    @GetMapping("/")
    public ResponseData<List<Role>> getRole() {

        ResponseData<List<Role>> responseData = new ResponseData<>();

        try {
            responseData.setData(roleService.getRole());
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.FAILED);
        }
        return responseData;
    }

    //==> Insert Role
    @PostMapping("/")
    public ResponseData<RoleVo> create(@RequestBody RoleVo.RoleFormVo roleFormVo) {

        ResponseData<RoleVo> responseData = new ResponseData<>();

        try {
            roleService.saveRole(roleFormVo);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
        }
        return responseData;
    }
    
    //==> Update Role
    @PutMapping("/")
    public ResponseData<RoleVo> update(@RequestBody RoleVo.RoleFormVo roleFormVo) {
    	
    	ResponseData<RoleVo> responseData = new ResponseData<>();
    	
    	try {
    		roleService.saveRole(roleFormVo);
    		MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
    	} catch (Exception e) {
    		e.printStackTrace();
    		MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
    	}
    	return responseData;
    }

    //==> Delete
    @DeleteMapping("/{id}")
    public ResponseData<RoleVo> delete(@RequestParam Long id){
        ResponseData<RoleVo> responseData = new ResponseData<>();
        try {
            roleService.deleteRole(id);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Delete.SUCCESS);
        }catch (Exception e){
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Delete.FAILED);
        }
        return responseData;
    }
}
