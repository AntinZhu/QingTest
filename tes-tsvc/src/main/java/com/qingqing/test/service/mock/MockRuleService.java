package com.qingqing.test.service.mock;

import com.qingqing.test.domain.mock.MockRule;

import java.util.List;

/**
 * Created by zhujianxing on 2018/6/12.
 */
public interface MockRuleService {
    List<MockRule> selectAll();
}
