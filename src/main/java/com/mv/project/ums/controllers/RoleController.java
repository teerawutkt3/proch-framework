package com.mv.project.ums.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mv.project.common.beans.ResponseData;
import com.mv.project.common.constants.ProjectConstant;
import com.mv.project.common.utils.MessageUtil;
import com.mv.project.ums.entities.Role;
import com.mv.project.ums.services.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // ==> Get Role
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

    // ==> Find By Id
    @GetMapping("/{id}")
    public ResponseData<Role> findById(@PathVariable long id) {
        ResponseData<Role> responseData = new ResponseData<>();
        try {
            responseData.setData(roleService.findById(id));
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
        }
        return responseData;
    }

    // ==> Save & Update Role
    @PostMapping("/")
    public ResponseData<Role> save(@RequestBody Role role) {
        ResponseData<Role> responseData = new ResponseData<>();
        try {
            roleService.saveRole(role);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
        }
        return responseData;
    }

    // ==> Delete
    @DeleteMapping("/{id}")
    public ResponseData<?> delete(@PathVariable Long id) {
        ResponseData<?> responseData = new ResponseData<>();
        try {
            roleService.deleteRole(id);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Delete.SUCCESS);
        } catch (Exception e) {
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Delete.FAILED);
        }
        return responseData;
    }
}
