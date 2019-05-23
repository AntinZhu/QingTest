package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.TestInterfaceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestInterfaceParamMapper {
    int insert(TestInterfaceParam record);

    int update(TestInterfaceParam record);

    TestInterfaceParam selectById(Long id);

    List<TestInterfaceParam> selectByInterfaceId(@Param("interfaceId") Long interfaceId);

    int resetDefault(@Param("interfaceId") Long interfaceId);

    int setDefault(@Param("id") Long id);

    int deleteById(@Param("id") Long id);
}