package com.qingqing.test.util;

import com.qingqing.common.exception.QingQingRuntimeException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by zhujianxing on 2018/1/24.
 */
public class UrlUtil {

    public static String linkUrlRequestForObjectValue(Map<String, Object> paramMap){
        return linkUrlRequestForObjectValue(paramMap, "=", "&");
    }

    public static String linkUrlRequestForObjectValue(Map<String, Object> paramMap, String link, String split){
        StringBuilder sb = new StringBuilder();

        Iterator iterator = paramMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, Object> entry = (Entry)iterator.next();
            if(entry.getValue() != null && (entry.getValue().toString()).trim().length() > 0) {
                sb.append(entry.getKey() + link + entry.getValue().toString() + split);
            }
        }

        String result = sb.toString();
        if(result.endsWith(split)) {
            result = result.substring(0, result.length() - split.length());
        }

        return result;
    }


    public static String linkUrlRequestForStringValueWithUrlEncode(Map<String, String> paramMap, String encode){
        StringBuilder sb = new StringBuilder();

        Iterator iterator = paramMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, String> entry = (Entry)iterator.next();
            if(entry.getValue() != null && ((String)entry.getValue()).trim().length() > 0) {
                try {
                    sb.append((String)entry.getKey() + "=" + URLEncoder.encode((String)entry.getValue(), encode) + "&");
                } catch (UnsupportedEncodingException e) {
                    throw new QingQingRuntimeException("url encode error", e);
                }
            }
        }

        String result = sb.toString();
        if(result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public static String linkUrlRequestForStringValue(Map<String, String> paramMap){
        StringBuilder sb = new StringBuilder();

        Iterator iterator = paramMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, String> entry = (Entry)iterator.next();
            if(entry.getValue() != null && ((String)entry.getValue()).trim().length() > 0) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }

        String result = sb.toString();
        if(result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }
}
