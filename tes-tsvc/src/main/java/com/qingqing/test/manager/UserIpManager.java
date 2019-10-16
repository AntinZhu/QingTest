package com.qingqing.test.manager;

import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.service.user.TestUserIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/7/8.
 */
@Component
public class UserIpManager implements ISyncable{

    @Autowired
    private TestUserIpService testUserIpService;

    private Map<String, TestUserIp> userIpMapping;

    @PostConstruct
    public void init(){
        sync();
    }

    public String getUserNameByIp(String userIp){
        TestUserIp user = userIpMapping.get(userIp);
        if(user == null){
            return userIp;
        }

        return user.getUserName();
    }

    public TestUserIp getUserInfo(String userIp){
        return userIpMapping.get(userIp);
    }

    public boolean isUserIpExist(String userIp){
        return getUserInfo(userIp) != null;
    }

    @Override
    public void sync() {
        List<TestUserIp> userIpList = testUserIpService.selectAll();
        Map<String, TestUserIp> tmpMapping = new HashMap<String, TestUserIp>();
        for (TestUserIp testUserIp : userIpList) {
            tmpMapping.put(testUserIp.getUserIp(), testUserIp);
        }

        userIpMapping = tmpMapping;
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{SyncType.all, SyncType.user_ip};
    }
}
