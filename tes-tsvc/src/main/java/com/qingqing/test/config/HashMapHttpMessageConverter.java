package com.qingqing.test.config;

import com.qingqing.common.util.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Created by zhujianxing on 2018/8/24.
 */
public class HashMapHttpMessageConverter extends AbstractHttpMessageConverter<HashMap> {
    @Override
    protected boolean supports(Class<?> aClass) {
        return HashMap.class.isAssignableFrom(aClass);
    }

    @Override
    protected HashMap readInternal(Class<? extends HashMap> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(HashMap map, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        StringBuilder paramBuilder = new StringBuilder();
        for(Object item : map.entrySet()){
            Entry<String, String> entry = (Entry<String, String>)item;
            paramBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        String param = paramBuilder.toString();
        if(!StringUtils.isEmpty(param)){
            param = param.substring(0, param.length() - 1);
        }

        httpOutputMessage.getBody().write(param.getBytes());
    }
}
