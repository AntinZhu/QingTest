package com.qingqing.test.dao.test.mock;


import com.qingqing.test.domain.mock.MockRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockRuleMapper {
	List<MockRule> selectAll();

	MockRule findById(Long id);

	List<MockRule> selectByRuleType(String ruleType);

	void insert(MockRule mockRule);

	void markDeleted(@Param("id") Long id, @Param("isDeleted") boolean isDeleted);

	void updateRuleOrderNum(@Param("id") Long id, @Param("ruleOrderNum") Integer ruleOrderNum);

	void resetDefault(@Param("mockType") String mockType);

	void updateDefault(@Param("id") Long id, @Param("isDefault") boolean isDefault);

	void updateNotMock(@Param("id") Long id, @Param("notMock") boolean notMock);

	void update(MockRule mockRule);
}