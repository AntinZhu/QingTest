package com.qingqing.test.manager;

import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.encode.TripleDESUtil;
import com.qingqing.test.controller.MyWebSocket;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.dao.passport.UpdateMapper;
import com.qingqing.test.dao.user.UserPhoneMapper;
import com.qingqing.test.domain.phone.UserAndPhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhujianxing on 2019/3/1.
 */
@Component
public class PhoneNumberManager {

    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberManager.class);

    Long step = 100000L;

    @Autowired
    private UserPhoneMapper userPhoneMapper;
    @Autowired
    private UpdateMapper updateMapper;
    @Autowired
    private MyWebSocket myWebSocket;
    private volatile boolean running = false;
    private volatile Future<?> lastTask;

    private ExecutorService POOL = Executors.newFixedThreadPool(1);

    public Integer sync() throws Exception {
        if(isRunning()){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "task running", "任务正在进行中"), "task running");
        }

        int totalCount = 0;
        try{
            running = true;
            updateMapper.insert("TRUNCATE t_user_login_account");
            totalCount += sync(UserType.ta);
            totalCount += sync(UserType.teacher);
            totalCount += sync(UserType.student);;
        }finally {
            running = false;
        }

        return totalCount;
    }

    private boolean isRunning(){
        if(running){
            return true;
        }

        if(lastTask != null){
            try {
                lastTask.get(500, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
               return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private Integer sync(UserType userType) throws Exception {
        Long maxUserId = selectMax(userType);

        Long startId = 0L;
        while(startId < maxUserId){
            update(userType, startId, startId + step, maxUserId);
            startId += step;
        }

        return maxUserId.intValue();
    }

    private Long selectMax(UserType userType){
        switch (userType){
            case student:
                return userPhoneMapper.selectMaxStudentId();
            case ta:
                return userPhoneMapper.selectMaxAssistantId();
            case teacher:
                return userPhoneMapper.selectMaxTeacherId();
            default:
                throw new QingQingRuntimeException("unknown userType for value:" + userType);
        }
    }

    private List<UserAndPhoneNumber> getUserAndPhoneNumber(UserType userType, Long startId, Long endId){
        switch (userType){
            case student:
                return userPhoneMapper.selectStudentList(startId, endId);
            case ta:
                return userPhoneMapper.selectAssistantList(startId, endId);
            case teacher:
                return userPhoneMapper.selectTeacherList(startId, endId);
            default:
                throw new QingQingRuntimeException("unknown userType for value:" + userType);
        }
    }

    private void update(final UserType userType, final Long startId, final Long endId,final  Long total) throws Exception {
        lastTask = POOL.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<UserAndPhoneNumber> phoneNumberList = getUserAndPhoneNumber(userType, startId, endId);
                    updateMapper.insert(buildSql(phoneNumberList));
                    notifyWS(userType, startId, endId, total);
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        });
    }

    private void notifyWS(final UserType userType, final Long startId, final Long endId,final  Long total) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("userType", userType);
        obj.put("start", startId);
        obj.put("end", Math.min(endId, total));
        obj.put("total", total);
        obj.put("desc", String.format("用户类型：%s start;%s end:%s total:%s", userType, startId, Math.min(endId, total), total));

        myWebSocket.sendMessage(obj.toJSONString());
    }

    private String buildSql(List<UserAndPhoneNumber> phoneNumberList) throws Exception {
        int size = phoneNumberList.size();

        StringBuilder sb = new StringBuilder("insert into t_user_login_account( user_type, account_type, user_id, account_name, create_time, is_deleted) values");
        for (int i = 0; i < phoneNumberList.size(); i++) {
            UserAndPhoneNumber phoneNumber = phoneNumberList.get(i);
            sb.append(String.format("(%s, 0, %s,'%s', now(), 0)", phoneNumber.getUserType().getValue(), phoneNumber.getUserId(), TripleDESUtil.encrypt(phoneNumber.getPhoneNumber()).trim()));
            if(i != size - 1){
                sb.append(",");
            }
        }

        sb.append(" on duplicate key update user_id = values(user_id)");

        return sb.toString();
    }
}
