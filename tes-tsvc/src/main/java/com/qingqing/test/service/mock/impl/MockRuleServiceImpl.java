package com.qingqing.test.service.mock.impl;

import com.qingqing.test.dao.test.mock.MockRuleMapper;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.manager.mock.rule.MockRuleManager;
import com.qingqing.test.service.mock.MockRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2019/10/19.
 */
@Component
public class MockRuleServiceImpl implements MockRuleService {

    @Autowired
    private MockRuleMapper mapper;

    @Override
    public List<MockRule> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<MockRule> selectByRuleType(String ruleType) {
        return mapper.selectByRuleType(ruleType);
    }

    @Override
    public MockRule findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public void insert(MockRule mockRule) {
        mapper.insert(mockRule);
    }

    @Override
    public void markDelete(Long id, boolean isDeleted) {
        mapper.markDeleted(id, isDeleted);
    }

    @Override
    public void markDefault(Long id, boolean isDefault) {
        mapper.updateDefault(id, isDefault);
    }

    @Override
    public void resetDefault(String ruleType) {
        mapper.resetDefault(ruleType);
    }

    @Override
    public void updateRuleOrderNum(Long id, Integer ruleOrderNum) {
        mapper.updateRuleOrderNum(id, ruleOrderNum);
    }

    @Override
    public void update(MockRule mockRule) {
        mapper.update(mockRule);
    }
}
