package com.qingqing.test.domain.inter;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public enum CatelogRefType implements HasDefaultInterface<CatelogRefType>{
    unknown(-1),
    url(1), // 地址
    inter(2), // 接口
    cate(3), // 目录
    config(4), // 配置
    ;

    private Integer value;

    CatelogRefType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public CatelogRefType getDefault() {
        return unknown;
    }

    public static CatelogRefType valueOf(Integer value){
        for(CatelogRefType interfaceType : CatelogRefType.values()){
            if(interfaceType.getValue().equals(value)){
                return interfaceType;
            }
        }

        throw new ConvertException("unknown RequestType for value:" + value);
    }
}
