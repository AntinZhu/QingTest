package com.qingqing.test.domain.inter;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum InterfaceType implements HasDefaultInterface<InterfaceType>{
    unknown(-1),
    PB(1),
    PT(2),
    PI(3),
    ;

    private Integer value;

    InterfaceType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public InterfaceType getDefault() {
        return unknown;
    }

    public static InterfaceType valueOf(Integer value){
        for(InterfaceType interfaceType : InterfaceType.values()){
            if(interfaceType.getValue().equals(value)){
                return interfaceType;
            }
        }

        throw new ConvertException("unknown InterfaceType for value:" + value);
    }
}
