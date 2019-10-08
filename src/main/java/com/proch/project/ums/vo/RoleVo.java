package com.proch.project.ums.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleVo {

    private String roleName;
    private String description;

    @Getter
    @Setter
    public static class RoleFormVo{
    	private String idStr;
        private String roleName;
        private String description;
    }
}
