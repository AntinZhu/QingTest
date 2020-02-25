package com.qingqing.test.aspect.validate;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.SimpleErrorCode;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.spring.filter.IpFilter;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Aspect
//@Component
public class IpLoginUserValidAspect {

    private static final Logger logger = LoggerFactory.getLogger(IpLoginUserValidAspect.class);

    @Autowired
    private UserIpManager userIpManager;

    @Before(value = "@annotation(ipUserValid)", argNames = "ipUserValid")
    public void before(IpLoginValid ipUserValid) throws Throwable {
        String requestUserIp = IpFilter.getRequestUserIp();
        TestUserIp userIp = userIpManager.getUserInfoExcTmp(requestUserIp);
        if(userIp == null){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "tmp user invalid", "临时登陆用户无法使用该功能"), "haha for userIp:" + requestUserIp);
        }

        IpLoginValidType validType = ipUserValid.validaType();
        if(validType == IpLoginValidType.assign){
            if(!ipUserValid.assignIp().equals(requestUserIp)){
                throw new ErrorCodeException(new SimpleErrorCode(1001, "tmp user invalid", "临时登陆用户无法使用该功能"), "hehe for userIp:" + requestUserIp);
            }
        }
    }
}
