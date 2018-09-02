package com.qingqing.test.manager;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.test.bean.inter.request.InterfaceInvokeRequest;
import com.qingqing.test.bean.inter.response.TestInterfaceBean;
import com.qingqing.test.client.ApiPtClient;
import com.qingqing.test.controller.errorcode.TestInterfaceErrorCode;
import com.qingqing.test.domain.inter.ParamEncodeType;
import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.service.inter.TestInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceManager {

    @Autowired
    private TestInterfaceService testInterfaceService;
    @Autowired
    private ApiPtClient apiPtClient;

    public TestInterfaceBean getInterfaceBean(Long interfaceId){
        TestInterface testInterface = testInterfaceService.findById(interfaceId);

        TestInterfaceBean resultBean = new TestInterfaceBean();
        resultBean.setInter(testInterface);
        return resultBean;
    }

    public String invoke(InterfaceInvokeRequest requestBean){
        Long interfaceId = requestBean.getInterfaceId();
        TestInterface testInterface = testInterfaceService.findById(interfaceId);
        if(testInterface == null){
            throw new ErrorCodeException(TestInterfaceErrorCode.unknown_test_interface, "unknown interface for id:" + interfaceId);
        }

        return apiPtClient.studentCommonRequest(testInterface.getInterfaceUrl(), requestBean.getParam(), requestBean.getRequestUserId());
    }

    private String encodeParamValue(ParamEncodeType encodeType, String paramValue){
        if(encodeType != null){
            switch (encodeType){
                case order_id:
                    return OrderIdEncoder.encodeOrderId(Long.parseLong(paramValue));
                case student_id:
                    return UserIdEncoder.encodeUser(UserType.student, Long.parseLong(paramValue));
                case teacher_id:
                    return UserIdEncoder.encodeUser(UserType.teacher, Long.parseLong(paramValue));
            }
        }

        return paramValue;
    }
}
