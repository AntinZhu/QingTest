package com.qingqing.test.manager.mock.rule.impl;

import com.alibaba.fastjson.JSONObject;
import com.qingqing.test.manager.mock.rule.IRuleHandler;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public class ValueMatchRuleHandler implements IRuleHandler {

    private static final String VALUE_PARAM = "value";

    @Override
    public boolean meetRule(String ruleValue, String matchValue) {
        JSONObject obj = JSONObject.parseObject(ruleValue);
        String value = obj.getString(VALUE_PARAM);

        return value.equals(matchValue);
    }
}
