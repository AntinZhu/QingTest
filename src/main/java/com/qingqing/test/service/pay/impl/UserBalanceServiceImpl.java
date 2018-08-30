package com.qingqing.test.service.pay.impl;

import com.qingqing.test.dao.data.common.DataDatabaseMapper;
import com.qingqing.test.service.pay.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/8/28.
 */
@Component
public class UserBalanceServiceImpl implements UserBalanceService {

    @Autowired
    private DataDatabaseMapper dataDatabaseMapper;

    @Override
    public boolean addUserBalance(Long userId, Double addAmount) {
        String sql = "update t_user_balance set balance = balance + " + addAmount + " where user_id = " + userId;
        return dataDatabaseMapper.update(sql) > 0;
    }
}