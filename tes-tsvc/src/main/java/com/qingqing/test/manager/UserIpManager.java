package com.qingqing.test.manager;

import com.google.common.collect.Sets;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.bean.config.ITestConfigNotify;
import com.qingqing.test.domain.user.IpStatus;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.manager.config.TestConfigManager;
import com.qingqing.test.service.user.TestUserIpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by zhujianxing on 2019/7/8.
 */
@Component
public class UserIpManager implements ISyncable, ITestConfigNotify {

    private static final Logger logger = LoggerFactory.getLogger(UserIpManager.class);

    private static final String TMP_ALWAYS_ALLOW_USERNAME_CONFIG_KEY = "tmp_user_allow_set";
    private static Set<String> TMP_ALWAYS_ALLOW_USERNAME_SET = Collections.emptySet();

    @Autowired
    private TestUserIpService testUserIpService;
    @Autowired
    private TestConfigManager testConfigManager;

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

    public void addTmpUser(String userName, String userIp, IpStatus ipStatus){
        TestUserIp testUserIp = new TestUserIp();
        testUserIp.setId(0L);
        testUserIp.setUserIp(userIp);
        testUserIp.setUserName(userName);
        testUserIp.setIpStatus(ipStatus);
        Date createTime;
        if(TMP_ALWAYS_ALLOW_USERNAME_SET.contains(userName)){
            createTime = TimeUtil.dayAfterNow(1);
        }else{
            createTime = new Date();
        }
        testUserIp.setCreateTime(createTime);

        tmpUserMapping.put(userIp, testUserIp);
    }

    @Override
    public void notifyChange() {
        TMP_ALWAYS_ALLOW_USERNAME_SET = Sets.newHashSet(JsonUtil.parserJsonList(testConfigManager.getConfigValue(TMP_ALWAYS_ALLOW_USERNAME_CONFIG_KEY, "[]"), String.class));
    }
}
