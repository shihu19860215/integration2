package com.shihu.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        return (str == null || EMPTY.equals(str));
    }
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }

    /**
     *首字母转小写
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * A String for a space character.
     */
    public static final String SPACE = " ";

    /**
     * The empty String {@code ""}.
     */
    public static final String EMPTY = "";
}
