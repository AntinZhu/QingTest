package com.qingqing.test.domain.user;

import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2020/4/17.
 */
public enum IpStatus implements HasDefaultInterface<IpStatus>{
    enable(1), // 可用
    black(2) // 黑名单
    ;

    private final Integer value;

    IpStatus(Integer value) {
        this.value = value;
    }


    @Override
    public IpStatus getDefault() {
        return enable;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
