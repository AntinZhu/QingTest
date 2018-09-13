package com.qingqing.test.domain.inter;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum RequestType implements HasDefaultInterface<RequestType>{
    unknown(-1),
    POST(1),
    GET(2),
    ;

    private Integer value;

    RequestType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public RequestType getDefault() {
        return unknown;
    }

    public static RequestType valueOf(Integer value){
        for(RequestType interfaceType : RequestType.values()){
            if(interfaceType.getValue().equals(value)){
                return interfaceType;
            }
        }

        throw new ConvertException("unknown RequestType for value:" + value);
    }
}
