package com.qingqing.test.util;

import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * Created by zhujianxing on 2018/8/24.
 */
public class ObjectUtil {

    public static TreeMap<String, Object> objectToMap(Object object) throws IllegalArgumentException, IllegalAccessException {
        TreeMap<String, Object> map = new TreeMap();

        for(Class cls = object.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            Field[] fields = cls.getDeclaredFields();
            int fieldLength = fields.length;

            for(int idx = 0; idx < fieldLength; ++idx) {
                Field f = fields[idx];
                f.setAccessible(true);
                String fieldName = f.getName();
                Object value = f.get(object);
                if(value != null){
                    map.put(fieldName, value);
                }
            }
        }

        return map;
    }
}
