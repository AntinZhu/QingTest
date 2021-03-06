package com.qingqing.test.manager;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.StringUtils;
import com.qingqing.test.aspect.masterslave.QingReadSlaveDataSource;
import com.qingqing.test.bean.inter.CatelogBean;
import com.qingqing.test.bean.inter.SaveCatelogBean;
import com.qingqing.test.bean.inter.SaveInterfaceBean;
import com.qingqing.test.bean.inter.request.InterfaceInvokeRequest;
import com.qingqing.test.bean.inter.response.TestInterfaceBean;
import com.qingqing.test.client.PbClient;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.config.TestSourceDataConfig;
import com.qingqing.test.controller.errorcode.TestInterfaceErrorCode;
import com.qingqing.test.domain.inter.CatelogRefType;
import com.qingqing.test.domain.inter.ParamEncodeType;
import com.qingqing.test.domain.inter.TestInterface;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import com.qingqing.test.service.inter.TestInterfaceParamService;
import com.qingqing.test.service.inter.TestInterfaceService;
import com.qingqing.test.service.inter.impl.TestInterfaceCatelogReadOnlyServiceImpl;
import com.qingqing.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
public class TestInterfaceManager implements ISyncable{

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
    private PbClient pbClient;
    @Autowired
    private UserService userService;
    @Autowired
    private TestInterfaceParamService testInterfaceParamService;
    @Autowired
    private TestInterfaceManager self;
    @Autowired
    private TestInterfaceCatelogReadOnlyServiceImpl testInterfaceCatelogReadOnlyService;

    @Transactional(transactionManager = TestSourceDataConfig.TX_MANAGER)
    public TestInterfaceCatelog saveCatelog(SaveCatelogBean saveBean, TestInterfaceCatelog parentCatelog){
        Long parentCatelogId;
        if(parentCatelog == null){ // 根目录
            parentCatelogId = null;
        }else{ // 子目录
            parentCatelogId = parentCatelog.getId();
        }

        return saveCatelog(parentCatelogId, saveBean.getRefType(), saveBean.getRefValue(), saveBean.getCatelogName(), null);
    }

    @Transactional(transactionManager = TestSourceDataConfig.TX_MANAGER)
    public Long saveTestInterface(SaveInterfaceBean saveBean, TestInterfaceCatelog parentCatelog){
        TestInterface testInterface = saveBean.getInter();
        if(testInterface.getId() == null){
            testInterfaceService.save(testInterface);
            Long interfaceId = testInterface.getId();

            saveCatelog(parentCatelog.getId(), CatelogRefType.inter, String.valueOf(interfaceId), saveBean.getCatelogName(), saveBean.getClazz());
        }else{
            Long interfaceId = testInterface.getId();
            testInterfaceService.update(testInterface);
            TestInterfaceCatelog catelog = catelogService.selectByRefTypeAndRefValue(CatelogRefType.inter, String.valueOf(interfaceId));
            if(catelog == null || !catelog.getId().equals(parentCatelog.getId()) || !catelog.getCatelogName().equals(parentCatelog.getCatelogName())){
                saveCatelog(parentCatelog.getId(), CatelogRefType.inter, String.valueOf(interfaceId), saveBean.getCatelogName(), saveBean.getClazz());

                if(catelog != null){
                    catelogService.deletedById(catelog.getId());
                }
            }
        }

        return testInterface.getId();
    }

    public TestInterfaceCatelog saveCatelog(Long parentCatelogId, CatelogRefType refType, String refId, String catelogName, String clazz){
        TestInterfaceCatelog catelog = new TestInterfaceCatelog();

        String catelogIndex;
        Integer sortNum;
        if(parentCatelogId != null){
            TestInterfaceCatelog parentCatelog = catelogService.selectForUpdate(parentCatelogId);
            sortNum = parentCatelog.getSubItemCnt() + 1;
            catelogIndex = parentCatelog.getCacheCatelogIndex() + "-" + sortNum;

            catelogService.incSubItemCnt(parentCatelog.getId());
        }else{
            sortNum = getNextRootCateogIndex();
            catelogIndex = String.valueOf(sortNum);
        }

        catelog.setCatelogName(catelogName);
        catelog.setRefType(refType);
        catelog.setRefValue(refId);
        catelog.setDeleted(Boolean.FALSE);
        catelog.setParentCatelogId(parentCatelogId == null? 0L : parentCatelogId);
        catelog.setSortNum(sortNum);
        catelog.setSubItemCnt(0);
        catelog.setClazz(clazz);
        catelog.setCacheCatelogIndex(catelogIndex);

        catelogService.save(catelog);

        return catelog;
    }

    private synchronized Integer getNextRootCateogIndex(){
        Integer nextCatelogIndex = 0;
        List<TestInterfaceCatelog> catelogList = catelogService.selectAll();
        for(TestInterfaceCatelog catelog : catelogList){
            if(catelog.getParentCatelogId() == 0L){
                Integer catelogIndex = catelog.getSortNum();
                if(catelogIndex != null && catelogIndex.compareTo(nextCatelogIndex) > 0){
                    nextCatelogIndex = catelogIndex;
                }
            }
        }

        return nextCatelogIndex + 1;
    }

    @QingReadSlaveDataSource
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
        resultBean.setParamList(testInterfaceParamService.selectListByInterfaceId(interfaceId));
        return resultBean;
    }

    public List<TestInterfaceCatelog> getCatelogLinkList(TestInterfaceCatelog catelog){
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

    private String generateCatelogIndex(Map<Long, TestInterfaceCatelog> idMapping, TestInterfaceCatelog catelog){
        String catelogIndex = String.valueOf(catelog.getSortNum());

        Long parentCatelogId = catelog.getParentCatelogId();
        while (parentCatelogId != null && parentCatelogId != 0L){
            TestInterfaceCatelog parentCatelog = idMapping.get(parentCatelogId);
            if(parentCatelog == null){
                break;
            }

            catelogIndex = parentCatelog.getSortNum() + "-" + catelogIndex;
            parentCatelogId = parentCatelog.getParentCatelogId();
        }

        return catelogIndex;
    }

    @Transactional(transactionManager = TestSourceDataConfig.TX_MANAGER)
    public List<CatelogBean> getCatelogList(){
        List<TestInterfaceCatelog> allCatelogs = testInterfaceCatelogService.selectAll();
        return getCatelogBeanList(allCatelogs);
    }


    private List<CatelogBean> getCatelogBeanList(List<TestInterfaceCatelog> allCatelogs){
        Collections.sort(allCatelogs, new Comparator<TestInterfaceCatelog>(){
            @Override
            public int compare(TestInterfaceCatelog o1, TestInterfaceCatelog o2) {
                int result = o1.getParentCatelogId().compareTo(o2.getParentCatelogId());
                if(result == 0){
                    result = o1.getSortNum().compareTo(o2.getSortNum());
                }
                return result;
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

        String headers = CollectionsUtil.isNullOrEmpty(requestBean.getHeaders())? "": JsonUtil.format(requestBean.getHeaders());
        switch (testInterface.getInterfaceType()){
            case PT:
                switch (requestBean.getRequestUserType()){
                    case student:
                    case teacher:
                    case ta:
                        switch (testInterface.getRequestType()){
                            case POST:
                                return ptClient.commonRequest(requestBean.getRequestUrl(), requestBean.getParam(), requestBean.getRequestUserId(), requestBean.getRequestUserType(), headers);
                            case GET:
                                return ptClient.commonGetRequest(requestBean.getRequestUrl(), requestBean.getParam(), requestBean.getRequestUserId(), requestBean.getRequestUserType(), headers);
                            default:
                                throw new ErrorCodeException(TestInterfaceErrorCode.unsupport_request_type, "unsupport request type for value:" + testInterface.getRequestType());
                        }
                    default:
                        throw new ErrorCodeException(TestInterfaceErrorCode.unsupport_request_user_type, "unsupport request user type for value:" + testInterface.getRequestUserType());
                }
            case PI:
                switch (testInterface.getRequestType()){
                    case POST:
                        return piClient.commonRequest(requestBean.getRequestUrl(), requestBean.getParam(), requestBean.getRequestUserId(), requestBean.getRequestUserType(), headers);
                    case GET:
                        return piClient.commonGetRequest(requestBean.getRequestUrl(), requestBean.getParam(), requestBean.getRequestUserId(), requestBean.getRequestUserType(), headers);
                    default:
                        throw new ErrorCodeException(TestInterfaceErrorCode.unsupport_request_type, "unsupport request type for value:" + testInterface.getRequestType());
                }
            case PB:
                switch (testInterface.getRequestType()){
                    case POST:
                        return pbClient.commonRequest(requestBean.getRequestUrl(), requestBean.getParam(), headers);
                    case GET:
                        return pbClient.commonGetRequest(requestBean.getRequestUrl(), requestBean.getParam(), headers);
                    default:
                        throw new ErrorCodeException(TestInterfaceErrorCode.unsupport_request_type, "unsupport request type for value:" + testInterface.getRequestType());
                }
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

    @Override
    @Scheduled(cron = "0 30 * * * *")
    public void sync() {
        List<TestInterfaceCatelog> allCatelogs = testInterfaceCatelogService.selectAll();
        Map<Long, TestInterfaceCatelog> idMapping = CollectionsUtil.mapComposerId(allCatelogs, TestInterfaceCatelog.ID_COMPOSER);

        for (TestInterfaceCatelog catelog : allCatelogs) {
            String newCatelogIndex = generateCatelogIndex(idMapping, catelog);
            if(!newCatelogIndex.equals(catelog.getCacheCatelogIndex())){
                catelogService.updateCatelogIndex(catelog.getId(), newCatelogIndex);
            }
        }
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{SyncType.all, SyncType.catelog_index};
    }
}
