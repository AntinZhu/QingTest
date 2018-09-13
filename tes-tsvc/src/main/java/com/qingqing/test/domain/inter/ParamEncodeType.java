package com.qingqing.test.domain.inter;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum ParamEncodeType implements HasDefaultInterface<ParamEncodeType>{
    unknown(-1),
    no(0),
    order_id(1), // order加密
    student_id(2), // 学生ID加密
    teacher_id(3), // 老师ID加密
    ;

    private Integer value;

    ParamEncodeType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public ParamEncodeType getDefault() {
        return unknown;
    }

    public static ParamEncodeType valueOf(Integer value){
        for(ParamEncodeType interfaceType : ParamEncodeType.values()){
            if(interfaceType.getValue().equals(value)){
                return interfaceType;
            }
        }

        throw new ConvertException("unknown RequestType for value:" + value);
    }
}
