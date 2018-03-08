package com.shihu.tag.myshiro;

import com.shihu.shiro.entity.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PrincipalName extends TagSupport {
    @Override
    public int doStartTag() throws JspException {

        Object o = getSubject().getPrincipal();
        if(null!=o){
            Principal principal=(Principal)o;
            try {
                pageContext.getOut().write(principal.getName());
            } catch (IOException e) {
                throw new JspTagException("Error writing [" + principal.getName() + "] to JSP.", e);
            }
        }
        return SKIP_BODY;
    }

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
