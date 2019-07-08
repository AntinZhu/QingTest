package com.qingqing.test.service.tool;

import com.qingqing.test.domain.tool.TestCronTask;

import java.util.List;

/**
 * Created by zhujianxing on 2019/7/8.
 */
public interface TestCronTaskService {

    List<TestCronTask> selectAll();

    void add(TestCronTask task);
}
