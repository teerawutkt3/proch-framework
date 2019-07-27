package com.proch.project.common.utils;

import com.proch.project.common.bean.ResponseData;
import com.proch.project.common.constant.ProjectConstant;

public class MessageUtil {

    public static void setMessage(ResponseData<?> responseData, String message, String status){
        responseData.setMessage(message);
        responseData.setStatus(status);
    }
}
