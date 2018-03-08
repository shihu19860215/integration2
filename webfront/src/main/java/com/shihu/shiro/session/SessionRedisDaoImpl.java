package com.shihu.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class SessionRedisDaoImpl extends EnterpriseCacheSessionDAO {
    public Session readSession(Serializable sessionId) {
        Session session;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        Object o=request.getAttribute("shiro_session");
        if(null!=o){
            session=(Session) o;
        }else{
            session=super.readSession(sessionId);
            request.setAttribute("shiro_session",session);
        }
        return session;
    }
}
