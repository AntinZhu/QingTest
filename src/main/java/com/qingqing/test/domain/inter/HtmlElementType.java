package com.qingqing.test.domain.inter;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum HtmlElementType implements HasDefaultInterface<HtmlElementType>{
    unknown(-1),
    input(1), // 输入框
    ;

    private Integer value;

    HtmlElementType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public HtmlElementType getDefault() {
        return unknown;
    }

    public static HtmlElementType valueOf(Integer value){
        for(HtmlElementType interfaceType : HtmlElementType.values()){
            if(interfaceType.getValue().equals(value)){
                return interfaceType;
            }
        }

        throw new ConvertException("unknown RequestType for value:" + value);
    }
}
