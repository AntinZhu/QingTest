package com.qingqing.test.util;

import java.io.File;

/**
 * Created by zhujianxing on 2018/9/27.
 */
public class QingStringUtil {

    public static String toUnderlineStyle(String value){
        return humpToOtherStyle(value, "_");
    }

    public static String humpToOtherStyle(String value, String separator){
        StringBuilder sb = new StringBuilder();
        for(byte b : value.getBytes()){
            if(b >= 65 && b <= 90){
                sb.append(separator);
                b = (byte)(b + 32);
            }
            sb.append((char)b);
        }

        return sb.toString();
    }

    public static String betterReg(String value){
        StringBuilder sb = new StringBuilder();
        for(char c : value.toCharArray()){
            if("\\".toCharArray()[0] == c){
                sb.append("\\\\");
            }else{
                sb.append(c);
            }
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
