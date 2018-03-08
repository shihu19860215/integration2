package com.shihu.service.impl;

import com.shihu.base.BaseServiceImpl;
import com.shihu.cache.CacheFactory;
import com.shihu.model.common.Role;
import com.shihu.model.common.VO.RoleVO;
import com.shihu.mybatis.dao.PermissionDao;
import com.shihu.mybatis.dao.RoleDao;
import com.shihu.service.RoleService;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleVO> implements RoleService{
    private Cache<Long,Role> roleCache;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;

    public Role getRole(Long id) {
        Cache<Long,Role> cache=getCache();
        Role role=cache.get(id);
        if(null==role){
            RoleVO roleVO=this.get(id);
            Set<String> set=permissionDao.listStringPermissionByRoleId(id);
            role=new Role(roleVO,set);
            cache.put(id,role);
        }
        return role;
    }

    public Cache<Long,Role> getCache(){
        if(null==roleCache){
            roleCache= CacheFactory.getCache(this.getClass().getName());
        }
        return roleCache;
    }

}
