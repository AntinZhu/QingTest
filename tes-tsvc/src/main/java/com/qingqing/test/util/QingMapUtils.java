package com.qingqing.test.util;

import com.google.common.collect.Maps;
import com.qingqing.common.util.CollectionsUtil;

import java.util.Collections;
import java.util.Map;

/**
 * Created by zhujianxing on 2020/4/20.
 */
public class QingMapUtils {

    public static final Map<String, Object> toObjectValueMap(Map<String, String> stringMap){
        if(CollectionsUtil.isNullOrEmpty(stringMap)){
            return Collections.emptyMap();
        }

        Map<String, Object> objMap = Maps.newHashMapWithExpectedSize(stringMap.size());
        for (Map.Entry<String, String> stringStringEntry : stringMap.entrySet()) {
            objMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return objMap;
    }
}
