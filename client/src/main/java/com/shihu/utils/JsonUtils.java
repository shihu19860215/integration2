package com.shihu.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

public class JsonUtils {
    private static Gson gson=new Gson();
    public static String toJson(Object o){
        return gson.toJson(o);
    }

    public static <T> T fromJson(String str,Type typeOfT){
        return gson.fromJson(str,typeOfT);
    }


    /**
     * 输出JSON
     *
     * @param response
     * @author SHANHY
     * @create 2017年4月4日
     */
    public static void writeJson(Object o, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(toJson(o));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
