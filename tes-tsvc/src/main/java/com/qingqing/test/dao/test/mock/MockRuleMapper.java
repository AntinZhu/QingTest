package com.qingqing.test.dao.test.mock;


import com.qingqing.test.domain.mock.MockRule;

import java.util.List;

public interface MockRuleMapper {
	List<MockRule> selectAll();

	void insert(MockRule mockRule);
}