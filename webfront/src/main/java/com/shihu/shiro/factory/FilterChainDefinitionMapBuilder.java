package com.shihu.shiro.factory;

import org.springframework.stereotype.Component;

import java.util.*;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> build(){
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>() ;
        map.put("/login","authc");
        map.put("/loginout","logout");
        map.put("/**","anon");




        /*
         <property name="filterChainDefinitions">
            &lt;!&ndash;anon表示可不需要登录态&ndash;&gt;
            &lt;!&ndash;authc表示需要登录态&ndash;&gt;
            &lt;!&ndash;前面的优先被匹配&ndash;&gt;
            <value>
                /test/hello.do = anon
                /user/login.do = anon

                /page/admin.do = roles[admin]
                /page/*.do = roles[user]

                /** = authc
            </value>
        </property>
         */
        return map ;
    }
}
