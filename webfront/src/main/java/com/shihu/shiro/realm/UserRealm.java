package com.shihu.shiro.realm;

import com.shihu.model.common.Role;
import com.shihu.service.RoleService;
import com.shihu.shiro.entity.Principal;
import com.shihu.model.common.VO.UserVO;
import com.shihu.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username=token.getUsername();
        UserVO userVO=userService.getUserVOByName(username);
        if(null==userVO)throw new UnknownAccountException();
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(new Principal(userVO.getId(),userVO.getUsername(),userVO.getRoleId()), userVO.getPassword(), ByteSource.Util.bytes(username),getName());

        return simpleAuthenticationInfo;
    }


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal=(Principal)principals.getPrimaryPrincipal();
        AuthorizationInfo authorizationInfo=principal.getAuthorizationInfo();
        if(null==authorizationInfo){
            Role role=roleService.getRole(principal.getRoleId());
            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.setStringPermissions(role.getStringpermissionSet());
            Set<String> roleSet=new HashSet<String>();
            roleSet.add(role.getName());
            simpleAuthorizationInfo.setRoles(roleSet);
            principal.setAuthorizationInfo(simpleAuthorizationInfo);
        }
        return authorizationInfo;
    }

}
