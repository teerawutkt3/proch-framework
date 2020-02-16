package com.mv.project.ums.vo;

import com.mv.project.ums.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVo {
    private User user;
    private List<Long> roleIds;
}
