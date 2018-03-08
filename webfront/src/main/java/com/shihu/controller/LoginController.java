package com.shihu.controller;

import com.shihu.shiro.entity.Principal;
import com.shihu.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Login(){
        Principal principal = UserUtils.getPrincipal();
        // 如果已经登录，则跳转到管理首页
        if(principal != null){
            return "redirect:/home";
        }
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Loginfail(){
        return "login";
    }

}
