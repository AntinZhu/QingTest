package com.qingqing.test.util;

import com.qingqing.common.util.TimeUtil;

import java.lang.reflect.Field;
import java.util.Date;
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

    public static void main(String[] args) {
        Date date = TimeUtil.getMonthStartTime(new Date());
        Date startTime = TimeUtil.getServalMinsLater(date, 14 * 60);
        Date endTime = TimeUtil.getServalMinsLater(TimeUtil.dayAfter(date, 5), 83);

        System.out.println(TimeUtil.dateToString(startTime, TimeUtil.DEFAULT_TIME_FORMAT));
        System.out.println(TimeUtil.dateToString(endTime, TimeUtil.DEFAULT_TIME_FORMAT));
    }
}
