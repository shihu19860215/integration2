package com.shihu.model.common.VO;

import com.shihu.base.BaseCommonVO;

public class RoleVO extends BaseCommonVO {
    private static final long serialVersionUID = -445742586859467475L;
    private String name;

    public String getName() {
        return name;
    }

    public RoleVO setName(String name) {
        this.name = name;
        return this;
    }
}
