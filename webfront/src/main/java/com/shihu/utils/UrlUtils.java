package com.shihu.utils;

public class UrlUtils {
    public static String getRedirectUrl(String redirectUrl,Object... parm){
        if(null!=parm&&parm.length>0){
            StringBuilder sb=new StringBuilder(redirectUrl);
            sb.append("?");
            for(int i=0;i<parm.length;i+=2){
                if(i!=0){
                    sb.append("&");
                }
                sb.append(parm[i])
                        .append("=")
                        .append(parm[i+1]);
            }
            return sb.toString();
        }else {
            return redirectUrl;
        }
    }
}
