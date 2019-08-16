package com.qingqing.test.manager;

import com.qingqing.common.onoff.ISwitchDeterminer;
import com.qingqing.test.domain.config.TestConfig;
import com.qingqing.test.service.config.TestConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
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

    private Map<String, String> configMap;

    @PostConstruct
    public void sync(){
        List<TestConfig> testConfigList = testConfigService.selectAll();

        Map<String, String> tmpConfigMap = new HashMap<>();
        for (TestConfig testConfig : testConfigList) {
            tmpConfigMap.put(testConfig.getConfigKey(), testConfig.getConfigValue());
        }

        configMap = tmpConfigMap;
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{all};
    }

    public String getConfigValue(String configKey, String defaultValue){
        return configMap.get(configKey);
    }

    @Override
    public boolean isOn(String s) {
        return isOn(s, false);
    }

    @Override
    public boolean isOn(String s, boolean defaultValue) {
        String configValue = configMap.get(s);
        if(configValue != null){
//            logger.info("config-key:" + s + " config-value:" + configValue);
           return "true".equals(configValue);
        }

        return defaultValue;
    }
}
