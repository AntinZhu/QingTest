package com.qingqing.test.dao.test.tool;

import com.qingqing.test.domain.tool.TestCronTask;

import java.util.List;

public interface TestCronTaskMapper {
    List<TestCronTask> selectAll();

    void add(TestCronTask cronTask);
}