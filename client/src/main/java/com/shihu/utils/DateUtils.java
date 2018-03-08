package com.shihu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    private static Map<String,SimpleDateFormat> simpleDateFormatMap=new HashMap<String,SimpleDateFormat>();
    public final static String DATE_PARSE_yyyyMMdd="yyyyMMdd";
    public final static String DATE_PARSE_YYYYMMddHHmmss="yyyy-MM-dd HH:mm:ss";

    public static boolean validateDateString(String str){
        if(null==str||str.length()!=8){
            return false;
        }
        try {
            int d=Integer.valueOf(str);
            int tmp;
            tmp=d/10000;
            if(tmp<2017||tmp>2100){
                return false;
            }
            tmp=d%10000/100;
            if(tmp<1||tmp>13){
                return false;
            }
            tmp=d%100;
            if(tmp<1||tmp>31){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return  true;
    }



    public static Date parse(String date, String parseStr){
        SimpleDateFormat simpleDateFormat=simpleDateFormatMap.get(parseStr);
        if(null==simpleDateFormat){
            simpleDateFormat=new SimpleDateFormat(parseStr);
            simpleDateFormatMap.put(parseStr,simpleDateFormat);
        }
        try {
            return  simpleDateFormat.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    public static String format(Date date,String parseStr){
        SimpleDateFormat simpleDateFormat=simpleDateFormatMap.get(parseStr);
        if(null==simpleDateFormat){
            simpleDateFormat=new SimpleDateFormat(parseStr);
            simpleDateFormatMap.put(parseStr,simpleDateFormat);
        }
        return  simpleDateFormat.format(date);
    }
}
