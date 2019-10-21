package com.qingqing.test.dao.test.mock;


import com.qingqing.test.domain.mock.MockType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockTypeMapper {
	List<MockType> selectAll();

	MockType findById(@Param("id") Long id);

	MockType findByMockType(@Param("mockType") String mockType);

	void insert(MockType mockType);

	void update(MockType mockType);

	void delete(Long id);
}