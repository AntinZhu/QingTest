package com.qingqing.test.domain.mock;

import com.qingqing.common.intf.HasDefaultInterface;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public enum RuleType implements HasDefaultInterface<RuleType> {
    unknown(-1),
    amount(1), // 金额规则
    ;

    private Integer value;

    RuleType(Integer value) {
        this.value = value;
    }

    @Override
    public RuleType getDefault() {
        return unknown;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
