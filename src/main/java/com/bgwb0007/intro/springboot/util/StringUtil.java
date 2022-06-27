package com.bgwb0007.intro.springboot.util;

import java.time.LocalDate;

public class StringUtil {
    public static String nvl(Object obj){
        if(obj == null || "".equals(obj)) return "";
        else return String.valueOf(obj);
    }
    public static String nvl(Object obj, String str){
        if(obj == null || "".equals(obj)) return str;
        else return String.valueOf(obj);
    }
}
