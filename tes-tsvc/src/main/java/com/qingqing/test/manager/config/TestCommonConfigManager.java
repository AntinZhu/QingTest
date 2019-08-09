package com.qingqing.test.manager.config;

import com.qingqing.test.bean.config.SaveCommonConfigBean;
import com.qingqing.test.bean.config.TestCommonConfigBean;
import com.qingqing.test.config.TestSourceDataConfig;
import com.qingqing.test.domain.config.TestCommonConfig;
import com.qingqing.test.domain.inter.CatelogRefType;
import com.qingqing.test.domain.inter.TestInterfaceCatelog;
import com.qingqing.test.manager.TestInterfaceManager;
import com.qingqing.test.service.config.TestCommonConfigService;
import com.qingqing.test.service.inter.TestInterfaceCatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhujianxing on 2019/8/9.
 */
@Component
public class TestCommonConfigManager {

    @Autowired
    private TestCommonConfigService testCommonConfigService;
    @Autowired
    private TestInterfaceManager testInterfaceManager;
    @Autowired
    private TestInterfaceCatelogService catelogService;

    @Transactional(transactionManager = TestSourceDataConfig.TX_MANAGER)
    public void saveCommonConfig(SaveCommonConfigBean bean){
        TestCommonConfig commonConfig = bean.getConfig();
        if (commonConfig.getId() == null){
            testCommonConfigService.insert(commonConfig);
            testInterfaceManager.saveCatelog(bean.getParentCatelogId(), CatelogRefType.config, String.valueOf(commonConfig.getId()), commonConfig.getConfigName(), null);
        }else{
            Long configId = commonConfig.getId();
            testCommonConfigService.update(commonConfig);
            TestInterfaceCatelog catelog = catelogService.selectByRefTypeAndRefValue(CatelogRefType.config, String.valueOf(configId));
            if(catelog == null || !catelog.getId().equals(bean.getParentCatelogId()) || !catelog.getCatelogName().equals(commonConfig.getConfigName())){
                testInterfaceManager.saveCatelog(bean.getParentCatelogId(), CatelogRefType.config, String.valueOf(commonConfig.getId()), commonConfig.getConfigName(), null);

                if(catelog != null){
                    catelogService.deletedById(catelog.getId());
                }
            }
        }
    }

    public TestCommonConfigBean getBeanById(Long id){
        TestCommonConfig commonConfig = testCommonConfigService.selectById(id);
        if(commonConfig == null || commonConfig.getDeleted()){
            return null;
        }


        TestInterfaceCatelog catelog = catelogService.selectByRefTypeAndRefValue(CatelogRefType.config, String.valueOf(commonConfig.getId()));
        List<TestInterfaceCatelog> linkList = null;
        if(catelog != null){
            linkList = testInterfaceManager.getCatelogLinkList(catelog);
        }

        TestCommonConfigBean result = new TestCommonConfigBean();
        result.setConfig(commonConfig);
        result.setCatelog(catelog);
        result.setParentCatelogList(linkList);

        return result;
    }
}
