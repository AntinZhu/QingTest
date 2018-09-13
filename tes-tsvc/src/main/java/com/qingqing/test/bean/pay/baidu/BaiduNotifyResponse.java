package com.qingqing.test.bean.pay.baidu;

import com.qingqing.common.exception.ConvertException;
import com.qingqing.common.intf.HasValueInterface;

import java.util.EnumSet;

/**
 * @description 支付类型, 对应于前端提交的支付类型，(com.qingqing.api.proto.v1.Pay.OrderPayType)
 * @note  4.6版本引入, 支持多种第三方支付类型
 */
public class BaiduNotifyResponse {

    private Integer status;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
