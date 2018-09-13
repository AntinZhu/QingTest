package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.TestInterfaceParam;

import java.util.List;

public interface TestInterfaceParamMapper {
    int insert(TestInterfaceParam record);

    List<TestInterfaceParam> selectByInterfaceId(Long interfaceId);
}