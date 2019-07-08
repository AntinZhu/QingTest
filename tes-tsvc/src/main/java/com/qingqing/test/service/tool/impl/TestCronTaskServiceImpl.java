package com.qingqing.test.service.tool.impl;

import com.qingqing.test.dao.test.tool.TestCronTaskMapper;
import com.qingqing.test.domain.tool.TestCronTask;
import com.qingqing.test.service.tool.TestCronTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2019/7/8.
 */
@Component
public class TestCronTaskServiceImpl implements TestCronTaskService {

    @Autowired
    private TestCronTaskMapper mapper;

    @Override
    public List<TestCronTask> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void add(TestCronTask task) {
        mapper.add(task);
    }

    @Override
    public TestCronTask findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public boolean update(Long id, String name, String url) {
        return mapper.update(id, name, url) > 0;
    }

    @Override
    public boolean deleted(Long id) {
        return mapper.delete(id) > 0;
    }
}
