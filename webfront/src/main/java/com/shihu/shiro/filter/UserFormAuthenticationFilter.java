package com.shihu.shiro.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {
    public static final String DEFAULT_MESSAGE_PARAM = "message";

    protected void setFailureAttribute(ServletRequest request, AuthenticationException e) {
        String className = e.getClass().getName(), message = "";
        if(e instanceof UnknownAccountException){
            message = "用户不存在, 请确认.";
        }else if (e instanceof IncorrectCredentialsException){
            message = "密码错误, 请重试.";
        }else if (e.getMessage() != null && e.getMessage().startsWith("msg:")){
            message = e.getMessage().replace("msg:","");
        }else{
            message = "系统出现点问题，请稍后再试！";
            e.printStackTrace(); // 输出到控制台
        }
        request.setAttribute(DEFAULT_MESSAGE_PARAM, message);
    }
}
