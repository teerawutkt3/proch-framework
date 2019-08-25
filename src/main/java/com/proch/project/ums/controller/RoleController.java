package com.proch.project.ums.controller;

import com.proch.project.common.utils.MessageUtil;
import com.proch.project.common.bean.ResponseData;
import com.proch.project.common.constant.ProjectConstant;
import com.proch.project.ums.entity.Role;
import com.proch.project.ums.service.RoleService;
import com.proch.project.ums.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseData<RoleVo> insertRole(@RequestBody RoleVo.RoleFormVo roleFormVo) {

        ResponseData<RoleVo> responseData = new ResponseData<>();

        try {
            roleService.insertRole(roleFormVo);
            MessageUtil.setMessageSuccess(responseData, ProjectConstant.ResponseMessage.Save.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.setMessageFail(responseData, ProjectConstant.ResponseMessage.Save.FAILED);
        }
        return responseData;
    }

    //==> Delete
    @DeleteMapping("/{id}")
    public ResponseData<RoleVo> deleteRole(@RequestParam Long id){
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
