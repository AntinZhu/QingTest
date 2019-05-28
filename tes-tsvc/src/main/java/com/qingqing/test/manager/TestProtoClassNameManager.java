package com.qingqing.test.manager;

import com.qingqing.test.domain.config.TestProtoClassName;
import com.qingqing.test.service.config.TestProtoClassNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/3/5.
 */
@Component
public class TestProtoClassNameManager implements ISyncable{

    @Autowired
    private TestProtoClassNameService testProtoClassNameService;

    private Map<String, List<String>> simpleFullMap;

    @PostConstruct
    public void sync(){
        List<TestProtoClassName> testConfigList = testProtoClassNameService.selectAll();

        Map<String, List<String>> tmpConfigMap = new HashMap<>();
        for (TestProtoClassName testConfig : testConfigList) {
            List<String> mapList = tmpConfigMap.get(testConfig.getSimpleName());
            if(mapList == null){
                mapList = new LinkedList<>();
                tmpConfigMap.put(testConfig.getSimpleName(), mapList);
            }

            mapList.add(testConfig.getFullClassName());
        }

        simpleFullMap = tmpConfigMap;
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{SyncType.all, SyncType.proto_name};
    }

    public List<String> getFullClassName(String simpleName){
        return simpleFullMap.get(simpleName);
    }
}
