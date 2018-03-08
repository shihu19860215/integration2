package com.shihu.base;

import com.shihu.config.Global;
import com.shihu.entity.AjaxResult;
import com.shihu.utils.JsonUtils;
import com.shihu.utils.WebUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
            if (WebUtils.isAjaxRequest(request)) {
            AjaxResult ajaxResult=new AjaxResult();
            ajaxResult.setState(0);
            ajaxResult.setInfo("没有权限");
            JsonUtils.writeJson(ajaxResult,response);
            return null;
        } else {
            return Global.REDIRECT_HOME;
        }
    }
}
