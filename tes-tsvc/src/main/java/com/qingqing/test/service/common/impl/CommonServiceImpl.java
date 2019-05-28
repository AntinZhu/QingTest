package com.qingqing.test.service.common.impl;

import com.qingqing.test.dao.test.common.CommonMapper;
import com.qingqing.test.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper mapper;

    @Override
    public void insert(String sql) {
        mapper.insert(sql);
    }

    @Override
    public void delete(String sql) {
        mapper.delete(sql);
    }
}
