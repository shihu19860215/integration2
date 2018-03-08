package com.shihu.model.common.VO;

import com.shihu.base.BaseCommonVO;

public class UserVO extends BaseCommonVO {
    private static final long serialVersionUID = -4621926816892342479L;
    /**
     * 角色ID
     */
    private Long roleId;
    private String username;
    private String password;

    public Long getRoleId() {
        return roleId;
    }

    public UserVO setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
