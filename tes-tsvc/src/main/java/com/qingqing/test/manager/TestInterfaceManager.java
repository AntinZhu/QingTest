package com.qingqing.test.manager;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.StringUtils;
import com.qingqing.test.bean.inter.CatelogBean;
import com.qingqing.test.bean.inter.SaveCatelogBean;
import com.qingqing.test.bean.inter.SaveInterfaceBean;
import com.qingqing.test.bean.inter.request.InterfaceInvokeRequest;
import com.qingqing.test.bean.inter.response.TestInterfaceBean;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.controller.errorcode.TestInterfaceErrorCode;
import com.qingqing.test.domain.inter.CatelogRefType;
import com.qingqing.test.domain.inter.ParamEncodeType;
import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import com.qingqing.test.service.inter.TestInterfaceService;
import com.qingqing.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private TestInterfaceCatelogService catelogService;
    @Autowired
    private PtClient ptClient;
    @Autowired
    private PiClient piClient;
    @Autowired
    private UserService userService;

    @Transactional
    public TestInterfaceCatelog saveCatelog(SaveCatelogBean saveBean, TestInterfaceCatelog parentCatelog){
        Long parentCatelogId;
        if(parentCatelog == null){ // 根目录
            parentCatelogId = null;
        }else{ // 子目录
            parentCatelogId = parentCatelog.getId();
        }

        return saveCatelog(parentCatelogId, CatelogRefType.cate, "#", saveBean.getCatelogName());
    }

    @Transactional
    public Long saveTestInterface(SaveInterfaceBean saveBean, TestInterfaceCatelog parentCatelog){
        TestInterface testInterface = saveBean.getInter();
        if(testInterface.getId() == null){
            testInterfaceService.save(testInterface);
            Long interfaceId = testInterface.getId();

            saveCatelog(parentCatelog.getId(), CatelogRefType.inter, String.valueOf(interfaceId), saveBean.getCatelogName());
        }else{
            Long interfaceId = testInterface.getId();
            testInterfaceService.update(testInterface);
            TestInterfaceCatelog catelog = catelogService.selectByRefTypeAndRefValue(CatelogRefType.inter, String.valueOf(interfaceId));
            if(catelog == null || !catelog.getId().equals(parentCatelog.getId()) || !catelog.getCatelogName().equals(parentCatelog.getCatelogName())){
                saveCatelog(parentCatelog.getId(), CatelogRefType.inter, String.valueOf(interfaceId), saveBean.getCatelogName());

                if(catelog != null){
                    catelogService.deletedById(catelog.getId());
                }
            }
        }

        return testInterface.getId();
    }

    private TestInterfaceCatelog saveCatelog(Long parentCatelogId, CatelogRefType refType, String refId, String catelogName){
        TestInterfaceCatelog catelog = new TestInterfaceCatelog();

        String catelogIndex;
        Integer sortNum;
        if(parentCatelogId != null){
            TestInterfaceCatelog parentCatelog = catelogService.selectForUpdate(parentCatelogId);
            sortNum = parentCatelog.getSubItemCnt() + 1;
            catelogIndex = parentCatelog.getCatelogIndex() + "-" + sortNum;

            catelogService.incSubItemCnt(parentCatelog.getId());
        }else{
            sortNum = getNextRootCateogIndex();
            catelogIndex = String.valueOf(sortNum);
        }

        catelog.setCatelogName(catelogName);
        catelog.setRefType(refType);
        catelog.setRefValue(refId);
        catelog.setDeleted(Boolean.FALSE);
        catelog.setCatelogIndex(catelogIndex);
        catelog.setParentCatelogId(parentCatelogId == null? 0L : parentCatelogId);
        catelog.setSortNum(sortNum);
        catelog.setSubItemCnt(0);

        catelogService.save(catelog);

        return catelog;
    }

    private synchronized Integer getNextRootCateogIndex(){
        Integer nextCatelogIndex = 0;
        List<TestInterfaceCatelog> catelogList = catelogService.selectAll();
        for(TestInterfaceCatelog catelog : catelogList){
            if(catelog.getCatelogIndex().indexOf("-") == -1){
                Integer catelogIndex = Integer.parseInt(catelog.getCatelogIndex());
                if(catelogIndex != null && catelogIndex.compareTo(nextCatelogIndex) > 0){
                    nextCatelogIndex = catelogIndex;
                }
            }
        }

        return nextCatelogIndex + 1;
    }

    public TestInterfaceBean getInterfaceBean(Long interfaceId){
        TestInterface testInterface = testInterfaceService.findById(interfaceId);

        TestInterfaceCatelog catelog = catelogService.selectByRefTypeAndRefValue(CatelogRefType.inter, String.valueOf(interfaceId));
        List<TestInterfaceCatelog> linkList = null;
        if(catelog != null){
            linkList = getCatelogLinkList(catelog);
        }

        TestInterfaceBean resultBean = new TestInterfaceBean();
        resultBean.setInter(testInterface);
        resultBean.setCatelog(catelog);
        resultBean.setParentCatelogList(linkList);
        return resultBean;
    }

    private List<TestInterfaceCatelog> getCatelogLinkList(TestInterfaceCatelog catelog){
        List<TestInterfaceCatelog> allCatelogs = testInterfaceCatelogService.selectAll();
        Map<Long, TestInterfaceCatelog> idMapping = CollectionsUtil.mapComposerId(allCatelogs, TestInterfaceCatelog.ID_COMPOSER);

        List<TestInterfaceCatelog> resultList = new LinkedList<>();
        Long parentCatelogId = catelog.getId();
        while (parentCatelogId != null && parentCatelogId != 0L){
            TestInterfaceCatelog parentCatelog = idMapping.get(parentCatelogId);
            if(parentCatelog == null){
                break;
            }

            resultList.add(parentCatelog);
            parentCatelogId = parentCatelog.getParentCatelogId();
        }

        return resultList;
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

       return getCatelogBeanList(allCatelogs, 0L, "");
    }

    private List<CatelogBean> getCatelogBeanList(List<TestInterfaceCatelog> allCatelogs, Long parentId, String parentIndex){
        if(!StringUtils.isEmpty(parentIndex)){
            parentIndex = parentIndex + "-";
        }

        List<CatelogBean> subList = null;
        for (TestInterfaceCatelog catelog : allCatelogs){
            String catelogIndex = String.valueOf(catelog.getSortNum());
            if(!StringUtils.isEmpty(catelogIndex)){
                if(!catelog.getParentCatelogId().equals(parentId)){
                    continue;
                }

                if(subList == null){
                    subList = new ArrayList<>();
                }
                String fullCatelogIndex = parentIndex + catelogIndex;

                CatelogBean catelogBean = new CatelogBean();
                catelogBean.setCatelog(catelog);
                catelogBean.setCatelogIndex(fullCatelogIndex);
                catelogBean.setSubCategoryList(getCatelogBeanList(allCatelogs, catelog.getId(), fullCatelogIndex));
                subList.add(catelogBean);
            }
        }

        return subList;
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
                    case ta:
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
                    return userService.encodeUser(UserType.student, Long.parseLong(paramValue));
                case teacher_id:
                    return userService.encodeUser(UserType.teacher, Long.parseLong(paramValue));
            }
        }

        return paramValue;
    }
}
