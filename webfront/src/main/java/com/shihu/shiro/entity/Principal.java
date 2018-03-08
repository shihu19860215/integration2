package com.shihu.shiro.entity;

import org.apache.shiro.authz.AuthorizationInfo;

import java.io.Serializable;

public class Principal implements Serializable{
    private static final long serialVersionUID = -5050206737143842506L;
    private Long id;
    private String name;
    private Long roleId;
    private String roleName;
    private AuthorizationInfo authorizationInfo;

    public Principal(Long id, String name,Long roleId) {
        this.id = id;
        this.name = name;
        this.roleId=roleId;
    }

    public Long getId() {
        return id;
    }

    public Principal setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Principal setName(String name) {
        this.name = name;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Principal setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Principal setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public AuthorizationInfo getAuthorizationInfo() {
        return authorizationInfo;
    }

    public Principal setAuthorizationInfo(AuthorizationInfo authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
        return this;
    }
}
