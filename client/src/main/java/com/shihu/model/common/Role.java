package com.shihu.model.common;

import com.shihu.base.BaseCommon;
import com.shihu.model.common.VO.PermissionVO;
import com.shihu.model.common.VO.RoleVO;

import java.util.List;
import java.util.Set;

public class Role extends BaseCommon{
    private static final long serialVersionUID = -3968501698434675576L;
    private String name;
    private Set<String> stringpermissionSet;

    public Role(RoleVO roleVO,Set<String> stringpermissionSet) {
        this.id=roleVO.getId();
        this.name=roleVO.getName();
        this.stringpermissionSet = stringpermissionSet;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public Set<String> getStringpermissionSet() {
        return stringpermissionSet;
    }

    public Role setStringpermissionSet(Set<String> stringpermissionSet) {
        this.stringpermissionSet = stringpermissionSet;
        return this;
    }
}
