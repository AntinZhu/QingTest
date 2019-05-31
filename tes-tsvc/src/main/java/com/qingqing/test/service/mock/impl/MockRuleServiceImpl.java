package com.qingqing.test.service.mock.impl;

import com.qingqing.test.dao.mock.MockRuleMapper;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.service.mock.MockRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhujianxing on 2018/6/12.
 */
@Service
public class MockRuleServiceImpl implements MockRuleService {

    @Autowired
    private MockRuleMapper mockRuleMapper;

    @Override
    public List<MockRule> selectAll() {
        return mockRuleMapper.selectAll();
    }
}
