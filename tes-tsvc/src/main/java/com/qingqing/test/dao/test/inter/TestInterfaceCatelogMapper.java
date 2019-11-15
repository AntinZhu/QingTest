package com.qingqing.test.dao.test.inter;

import com.qingqing.test.domain.inter.CatelogRefType;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestInterfaceCatelogMapper {
    int insert(TestInterfaceCatelog record);

    List<TestInterfaceCatelog> selectByAll();

    TestInterfaceCatelog findById(Long id);

    TestInterfaceCatelog selectForUpdate(Long id);

    void incSubItemCnt(Long id);

    void deleteById(Long id);

    TestInterfaceCatelog selectByRefTypeAndRefValue(@Param("refType") CatelogRefType refType, @Param("refValue") String refValue);

    void updateCatelogIndex(@Param("id") Long id, @Param("catelogIndex") String catelogIndex);
}