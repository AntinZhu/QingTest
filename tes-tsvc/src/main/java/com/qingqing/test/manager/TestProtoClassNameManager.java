package com.qingqing.test.manager;

import com.qingqing.test.aspect.delayinit.IQingInitDelayable;
import com.qingqing.test.aspect.delayinit.QingInitCheck;
import com.qingqing.test.domain.config.TestProtoClassName;
import com.qingqing.test.service.common.CommonService;
import com.qingqing.test.service.config.TestProtoClassNameService;
import com.qingqing.test.util.QingFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/3/5.
 */
@Component
public class TestProtoClassNameManager implements ISyncable, IQingInitDelayable {
    private static final Logger logger = LoggerFactory.getLogger(TestProtoClassNameManager.class);

    @Autowired
    private TestProtoClassNameService testProtoClassNameService;
    @Autowired
    private TestConfigManager testConfigManager;
    @Autowired
    private CommonService commonService;

    private Map<String, List<String>> simpleFullMap;

    @Value("${protobuf.init.filepath:null}")
    public String protoInitFile;

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

        if(protoInitFile != null && "true".equals(testConfigManager.getConfigValue("proto.auto.replace", "false"))){
            InputStream in = null;
            try{
                in = TestProtoClassNameManager.class.getResourceAsStream(protoInitFile);
                List<String> encodeList = QingFileUtils.readLines(in);
                for (String sql : encodeList) {
                    commonService.insert(sql);
                }
            }catch(Exception e){
                logger.error("init proto name fail", e);
            }finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }
        }
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{SyncType.all, SyncType.proto_name};
    }

    @QingInitCheck
    public List<String> getFullClassName(String simpleName){
        return simpleFullMap.get(simpleName);
    }

    @Override
    public void doInit() {
        sync();
    }

    @Override
    public boolean isNeedInit() {
        return simpleFullMap == null;
    }
}
