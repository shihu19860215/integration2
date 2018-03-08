package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.model.common.Role;
import com.shihu.model.common.VO.RoleVO;

public interface RoleService extends BaseService<RoleVO>{
    public Role getRole(Long id);
}
