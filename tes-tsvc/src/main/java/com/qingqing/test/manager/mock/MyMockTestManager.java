package com.qingqing.test.manager.mock;

import com.qingqing.test.aspect.mock.QingMock;
import com.qingqing.test.aspect.mock.QingMockType;
import com.qingqing.test.bean.common.UrlAndParam;
import com.qingqing.test.bean.common.UserWithDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Component
public class MyMockTestManager {
    private static final Logger logger = LoggerFactory.getLogger(MyMockTestManager.class);

    @Autowired
    private MyMockTestManager self;

    @QingMock(type = QingMockType.test, mockEnableSwitchKey = "abc", ruleParamExpression = "url")
    public String test(UrlAndParam urlAndParam, UserWithDataBean userData){
        self.a(urlAndParam, userData);
        self.b(urlAndParam, userData);

        return "normal";
    }

    @QingMock(type = QingMockType.test, mockEnableSwitchKey = "abc", ruleParamExpression = "url")
    public UrlAndParam a(UrlAndParam urlAndParam, UserWithDataBean userData){
        return new UrlAndParam();
    }

    @QingMock(type = QingMockType.test, mockEnableSwitchKey = "abc", ruleParamExpression = "url")
    public void b(UrlAndParam urlAndParam, UserWithDataBean userData){}

    @QingMock(type = QingMockType.test, mockEnableSwitchKey = "abc", ruleParamExpression = "url")
    public QingMockType c(UrlAndParam urlAndParam, UserWithDataBean userData){
        return QingMockType.unknown;
    }
}
