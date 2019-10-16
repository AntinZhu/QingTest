package com.qingqing.test.dao.test.mock;


import com.qingqing.test.domain.mock.MockType;

import java.util.List;

public interface MockTypeMapper {
	List<MockType> selectAll();

	void insert(MockType mockType);
}