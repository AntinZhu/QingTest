package com.qingqing.test.service.mock;

import com.qingqing.test.domain.mock.MockRule;

import java.util.List;

/**
 * Created by zhujianxing on 2019/10/19.
 */
public interface MockRuleService {

    List<MockRule> selectAll();

    List<MockRule> selectByRuleType(String ruleType);

    MockRule findById(Long id);

    void insert(MockRule mockRule);

    void update(MockRule mockRule);

    void markDelete(Long id, boolean isDeleted);

    void markDefault(Long id, boolean isDefault);

    void resetDefault(String ruleType);

    void updateRuleOrderNum(Long id, Integer ruleOrderNum);
}
