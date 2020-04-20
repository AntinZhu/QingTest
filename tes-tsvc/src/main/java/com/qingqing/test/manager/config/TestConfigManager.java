package com.qingqing.test.manager.config;

import com.google.common.collect.Maps;
import com.qingqing.common.onoff.ISwitchDeterminer;
import com.qingqing.test.bean.config.ITestConfigNotify;
import com.qingqing.test.config.TestSourceDataConfig;
import com.qingqing.test.domain.config.TestConfig;
import com.qingqing.test.manager.ISyncable;
import com.qingqing.test.service.config.TestConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.qingqing.test.manager.ISyncable.SyncType.all;

/**
 * Created by zhujianxing on 2019/3/5.
 */
@Component
public class TestConfigManager implements ISwitchDeterminer,ISyncable {
    private static final Logger logger = LoggerFactory.getLogger(TestConfigManager.class);

    @Autowired
    private TestConfigService testConfigService;
    @Autowired
    private List<ITestConfigNotify> testConfigNotifies;
    private Map<String, String> configMap;
    private Map<String, Object> jarVersionMap;

    @PostConstruct
    public void sync(){
        List<TestConfig> configList = testConfigService.selectAll();

        Map<String, String> tmpConfigMap = Maps.newHashMapWithExpectedSize(configList.size());
        Map<String, Object> tmpJarVersionMap = Maps.newHashMapWithExpectedSize(configList.size());
        for (TestConfig testConfig : configList) {
            tmpConfigMap.put(testConfig.getConfigKey(), testConfig.getConfigValue());
            if(testConfig.getConfigKey() != null && testConfig.getConfigKey().endsWith("_version")){
                tmpJarVersionMap.put(testConfig.getConfigKey(), testConfig.getConfigValue());
            }
        }

        configMap = tmpConfigMap;
        jarVersionMap = tmpJarVersionMap;

        for(ITestConfigNotify testConfigNotify : testConfigNotifies){
            try{
                testConfigNotify.notifyChange();
            }catch (Exception e){
                logger.error("test config notify fail", e);
            }
        }
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{all};
    }

    public String getConfigValue(String configKey, String defaultValue){
        String configValue =  configMap.get(configKey);
        if(configValue == null){
            return defaultValue;
        }

        return configValue;
    }

    @Override
    public boolean isOn(String s) {
        return isOn(s, false);
    }

    @Override
    public boolean isOn(String s, boolean defaultValue) {
        String configValue = configMap.get(s);
        if(configValue != null){
           return "true".equals(configValue);
        }

        return defaultValue;
    }

    public Map<String, Object> getJarVersionMap() {
        return Collections.unmodifiableMap(jarVersionMap);
    }

    @Transactional(transactionManager = TestSourceDataConfig.TX_MANAGER)
    public void addConfig(String configKey, String configValue){
        TestConfig testConfig = new TestConfig();
        testConfig.setConfigKey(configKey);
        testConfig.setConfigValue(configValue);
        testConfig.setDeleted(Boolean.FALSE);
        testConfigService.add(testConfig);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                sync();
            }
        });
    }
}
