package com.qingqing.test.manager.mock.rule;


import com.qingqing.test.domain.mock.IRuleData;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public interface IRuleHandler {

    boolean meetRule(String ruleValue, IRuleData ruleData);

}
