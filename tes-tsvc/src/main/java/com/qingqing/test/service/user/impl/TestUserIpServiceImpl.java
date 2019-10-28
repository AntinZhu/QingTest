package com.qingqing.test.service.user.impl;

import com.qingqing.test.dao.test.user.TestUserIpMapper;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.service.user.TestUserIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2019/7/8.
 */
@Component
public class TestUserIpServiceImpl implements TestUserIpService {

    @Autowired
    private TestUserIpMapper mapper;

    @Override
    public List<TestUserIp> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void insert(TestUserIp userIp) {
        mapper.insert(userIp);
    }

    @Override
    public TestUserIp findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public void updateDeleted(Long id, boolean isDeleted) {
        mapper.updateDeleted(id, isDeleted);
    }

    @Override
    public boolean updateUserHeadImage(String userIp, String headImage) {
        return mapper.updateHeadImage(userIp, headImage) > 0;
    }
}
