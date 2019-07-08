package com.qingqing.test.dao.test.tool;

import com.qingqing.test.domain.tool.TestCronTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestCronTaskMapper {
    List<TestCronTask> selectAll();

    void add(TestCronTask cronTask);

    TestCronTask findById(@Param("id") Long id);

    int update(@Param("id") Long id, @Param("name") String name, @Param("url") String url);

    int delete(@Param("id") Long id);
}