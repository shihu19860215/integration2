package com.shihu.utils;

import com.shihu.shiro.entity.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtils {
    public static Principal getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if (principal != null){
            return (Principal)principal;
        }
        return null;
    }
}
