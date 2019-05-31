package com.qingqing.test.manager.mock.rule.impl;

import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.test.domain.mock.IRuleData;
import com.qingqing.test.manager.mock.rule.IRuleHandler;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public class AmountRuleHandler implements IRuleHandler {

    private static final String START_AMOUNT_PARAM = "start";
    private static final String END_AMOUNT_PARAM = "end";

    @Override
    public boolean meetRule(String ruleValue, IRuleData ruleData) {
        JSONObject obj = JSONObject.parseObject(ruleValue);
        Double startAmount = obj.getDouble(START_AMOUNT_PARAM);
        if(startAmount == null || DoubleCompareUtil.lt(ruleData.getAmount(), startAmount)){
            return false;
        }

        Double endAmount = obj.getDouble(END_AMOUNT_PARAM);
        if(endAmount != null && DoubleCompareUtil.ge(ruleData.getAmount(), endAmount)){
            return false;
        }

        return true;
    }
}
