package com.qingqing.test.manager;

import com.qingqing.test.domain.config.TestConfig;
import com.qingqing.test.service.config.TestConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/3/5.
 */
@Component
public class TestConfigManager {

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

    public String getConfigValue(String configKey, String defaultValue){
        return configMap.get(configKey);
    }
}
