package com.qingqing.test.domain.order;

import com.qingqing.common.exception.ConvertException;

;

public enum FreezeType{
    /**
     * 迟到冻结
     */
    late(1),
    /**
     * 差评冻结
     */
    bad_comment(2),
    /**
     * 用户投诉冻结
     */
    user_complain(4),
    /**
     * 老师投诉冻结
     */
    teacher_complain(8),
    /**
     * 助教投诉冻结
     */
    assistant_complain(16),
    /**
     * 后台强制冻结
     */
    admin_force(32),;

    private Integer value;

    FreezeType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static final FreezeType valueOf(Integer value) {
        for (FreezeType status : FreezeType.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new ConvertException("unknown OrderCreateType value:" + value);
    }
}
