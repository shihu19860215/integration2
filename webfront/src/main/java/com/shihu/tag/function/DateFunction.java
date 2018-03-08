package com.shihu.tag.function;

import com.shihu.utils.DateUtils;

import java.util.Date;

public class DateFunction {
    public static String dateToString(Date date){
        return DateUtils.format(date,DateUtils.DATE_PARSE_YYYYMMddHHmmss);
    }
}
