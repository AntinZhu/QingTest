package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.TestInterfaceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestInterfaceParamMapper {
    int insert(TestInterfaceParam record);

    TestInterfaceParam selectById(Long id);

    List<TestInterfaceParam> selectByInterfaceId(@Param("interfaceId") Long interfaceId);
}