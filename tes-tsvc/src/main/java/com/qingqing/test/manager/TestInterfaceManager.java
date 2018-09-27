package com.qingqing.test.manager;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.test.bean.inter.CatelogBean;
import com.qingqing.test.bean.inter.request.InterfaceInvokeRequest;
import com.qingqing.test.bean.inter.response.TestInterfaceBean;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.controller.errorcode.TestInterfaceErrorCode;
import com.qingqing.test.domain.inter.ParamEncodeType;
import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import com.qingqing.test.service.inter.TestInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
@Component
public class TestInterfaceManager {

    @Autowired
    private TestInterfaceService testInterfaceService;
    @Autowired
    private TestInterfaceCatelogService testInterfaceCatelogService;
    @Autowired
    private PtClient ptClient;
    @Autowired
    private PiClient piClient;

    public TestInterfaceBean getInterfaceBean(Long interfaceId){
        TestInterface testInterface = testInterfaceService.findById(interfaceId);

        TestInterfaceBean resultBean = new TestInterfaceBean();
        resultBean.setInter(testInterface);
        return resultBean;
    }

    public List<CatelogBean> getCatelogList(){
        List<TestInterfaceCatelog> allCatelogs = testInterfaceCatelogService.selectAll();
        return getCatelogBeanList(allCatelogs);
    }

    private List<CatelogBean> getCatelogBeanList(List<TestInterfaceCatelog> allCatelogs){
        Collections.sort(allCatelogs, new Comparator<TestInterfaceCatelog>(){
            @Override
            public int compare(TestInterfaceCatelog o1, TestInterfaceCatelog o2) {
                return o1.getCatelogIndex().compareTo(o2.getCatelogIndex());
            }
        });

       return getCatelogBeanList(allCatelogs, 0, "");
    }

    private List<CatelogBean> getCatelogBeanList(List<TestInterfaceCatelog> allCatelogs, int startIdx, String parentIndex){
        List<CatelogBean> subList = null;
        while (startIdx < allCatelogs.size()){
            TestInterfaceCatelog catelog = allCatelogs.get(startIdx++);
            String catelogIndex = catelog.getCatelogIndex();
            if(!StringUtils.isEmpty(catelogIndex)){
                if(!catelogIndex.startsWith(parentIndex)){
                    break;
                }

                if(isSub(catelogIndex, parentIndex)){
                    if(subList == null){
                        subList = new ArrayList<>();
                    }

                    CatelogBean catelogBean = new CatelogBean();
                    catelogBean.setCatelog(catelog);
                    catelogBean.setSubCategoryList(getCatelogBeanList(allCatelogs, startIdx, catelog.getCatelogIndex()));
                    subList.add(catelogBean);
                }
            }
        }

        return subList;
    }

    private boolean isSub(String catelogIndex, String parentIndex){
        if(!StringUtils.isEmpty(catelogIndex)){
            if(catelogIndex.startsWith(parentIndex)){
                try{
                    int subIndex = StringUtils.isEmpty(parentIndex)? 0 : parentIndex.length() + 1;
                    Integer.valueOf(catelogIndex.substring(subIndex));
                    return true;
                }catch(Exception e){
                    return false;
                }
            }
        }

        return false;
    }

    public String invoke(InterfaceInvokeRequest requestBean){
        Long interfaceId = requestBean.getInterfaceId();
        TestInterface testInterface = testInterfaceService.findById(interfaceId);
        if(testInterface == null){
            throw new ErrorCodeException(TestInterfaceErrorCode.unknown_test_interface, "unknown interface for id:" + interfaceId);
        }

        switch (testInterface.getInterfaceType()){
            case PT:
                switch (testInterface.getRequestUserType()){
                    case student:
                    case teacher:
                        return ptClient.commonRequest(testInterface.getInterfaceUrl(), requestBean.getParam(), requestBean.getRequestUserId(), testInterface.getRequestUserType());
                    default:
                        throw new ErrorCodeException(TestInterfaceErrorCode.unsupport_request_user_type, "unsupport request user type for value:" + testInterface.getRequestUserType());
                }
            case PI:
                return piClient.commonRequest(testInterface.getInterfaceUrl(), requestBean.getParam(), requestBean.getRequestUserId(), testInterface.getRequestUserType());
            default:
                throw new ErrorCodeException(TestInterfaceErrorCode.unsupport_interface_type, "unsupport interface type for value:" + testInterface.getInterfaceType());
        }
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
