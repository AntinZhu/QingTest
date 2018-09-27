package com.qingqing.test.util;

/**
 * Created by zhujianxing on 2018/9/27.
 */
public class QingStringUtil {

    public static String toUnderlineStyle(String value){
        StringBuilder sb = new StringBuilder();
        for(byte b : value.getBytes()){
            if(b >= 65 && b <= 90){
                sb.append("_");
                b = (byte)(b + 32);
            }
            sb.append((char)b);
        }

        return sb.toString();
    }

    public static String generateGetMethod(String properties){
        return "get" + upperCase(properties);
    }

    public static String generateGetListMethod(String properties){
        return "get" + upperCase(properties) + "List";
    }

    public static String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(toUnderlineStyle("userName"));
    }
}
