package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.model.common.VO.PermissionVO;

import java.util.Set;

public interface PermissionDao extends BaseDao<PermissionVO> {
    public Set<String> listStringPermissionByRoleId(Long id);
}
