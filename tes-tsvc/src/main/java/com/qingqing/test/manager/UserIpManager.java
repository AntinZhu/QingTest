package com.qingqing.test.manager;

import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.service.user.TestUserIpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/7/8.
 */
@Component
public class UserIpManager implements ISyncable{

    private static final Logger logger = LoggerFactory.getLogger(UserIpManager.class);

    @Autowired
    private TestUserIpService testUserIpService;

    private Map<String, TestUserIp> userIpMapping;
    private Map<String, TestUserIp> userNameMapping;
    private Map<String, TestUserIp> tmpUserMapping;

    @PostConstruct
    public void init(){
        sync();
        tmpUserMapping = new HashMap<>();
    }

    public String getUserNameByIp(String userIp){
        TestUserIp user = userIpMapping.get(userIp);
        if(user == null){
            return userIp;
        }

        return user.getUserName();
    }

    public TestUserIp getUserInfoIncTmp(String userIp){
        TestUserIp user =  userIpMapping.get(userIp);
        if(user == null){
            user = tmpUserMapping.get(userIp);
            if(user != null){
                Date tenMinsAgo = TimeUtil.minutesAfterNow(-1);
//                logger.info("tmp user:createTime:{} validate time:{}", TimeUtil.dateToString(user.getCreateTime()), TimeUtil.dateToString(tenMinsAgo));
                if(user.getCreateTime().before(tenMinsAgo)){
                    user = null;
                }
            }
        }

        return user;
    }


    public TestUserIp getUserInfoExcTmp(String userIp){
       return userIpMapping.get(userIp);
    }

    public TestUserIp getUserInfoByName(String userName){
        return userNameMapping.get(userName);
    }

    public boolean isUserIpExist(String userIp){
        return getUserInfoIncTmp(userIp) != null;
    }

    @Override
    public void sync() {
        List<TestUserIp> userIpList = testUserIpService.selectAll();
        Map<String, TestUserIp> tmpMapping = new HashMap<String, TestUserIp>();
        Map<String, TestUserIp> tmpNameMapping = new HashMap<String, TestUserIp>();
        for (TestUserIp testUserIp : userIpList) {
            if(testUserIp.getDeleted()){
                continue;
            }

            tmpMapping.put(testUserIp.getUserIp(), testUserIp);
            tmpNameMapping.put(testUserIp.getUserName(), testUserIp);
        }

        userIpMapping = tmpMapping;
        userNameMapping = tmpNameMapping;
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{SyncType.all, SyncType.user_ip};
    }

    public void addTmpUser(String userName, String userIp){
        TestUserIp testUserIp = new TestUserIp();
        testUserIp.setId(0L);
        testUserIp.setCreateTime(new Date());
        testUserIp.setUserIp(userIp);
        testUserIp.setUserName(userName);

        tmpUserMapping.put(userIp, testUserIp);
    }
}
