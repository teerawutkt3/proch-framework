package com.proch.project.ums.controller;

import com.proch.project.common.utils.MessageUtil;
import com.proch.project.common.bean.ResponseData;
import com.proch.project.common.constant.ProjectConstant;
import com.proch.project.common.utils.UserLoginUtils;
import com.proch.project.ums.entity.User;
import com.proch.project.ums.service.UserSerivce;
import com.proch.project.ums.vo.RegisterVo;
import com.proch.project.ums.vo.UserProfileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            MessageUtil.setMessage(responseData, ProjectConstant.ResponseMessage.SUCCESS, ProjectConstant.ResponseStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
        }
        return responseData;
    }

    @PostMapping("/register")
    public ResponseData<User> register(@RequestBody RegisterVo registerVo){
        ResponseData<User> responseData = new ResponseData<>();

        try {
            userSerivce.register(registerVo);
            MessageUtil.setMessage(responseData, ProjectConstant.ResponseMessage.SUCCESS, ProjectConstant.ResponseStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            MessageUtil.setMessage(responseData, ProjectConstant.Error._403, ProjectConstant.ResponseStatus.FAILED);
        }
        return responseData;
    }

}