package com.qingqing.test.util;

import com.qingqing.common.auth.domain.User;
import com.qingqing.common.util.encode.RSAEncrypterCore;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ServerAuthUtil {

    private static final String PRIVATE_KEY = "qing_QiNG_jIA_JiAo!#%&@$^*";

    private static final long expire_ms =  1L* 60 * 60 * 1000;

    @SuppressWarnings("unused")
    private static final int VERSION = 0;

    public static final int USER_ID = 1;

    public static final int USER_TYPE = 2;

    public static final int EXPIRE_TIME_INDX = 3;

    public static final int DEVICE_INDX = 4;

    public static final String AUTH_SPLIT = "&";

    private static final Logger logger = LoggerFactory.getLogger(ServerAuthUtil.class);

    public static String generatorToken(long timestamp){
        String str  = PRIVATE_KEY + "-" + timestamp;
        return DigestUtils.md5Hex(str);
    }

    public static void  main(String argc[]){
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println("si:"+timestamp);
        String str  = PRIVATE_KEY + "-" + timestamp;
        System.out.println("tk:"+ DigestUtils.md5Hex(str));

        System.out.println(generatorToken(new Date().getTime()));;
    }

    public static String generatorEncryptedToken(User user, Long expireSeconds, String deviceId){
        String plain = generatorToken(user.getUserId(), user.getUserType().getValue(), expireSeconds, deviceId);
        return RSAEncrypterCore.getInstance().encrypt(plain).replace("\r\n", "");
    }

    public static String generatorToken(Long id, Integer type, Long expireSeconds, String deviceId){

        Long expireTime = System.currentTimeMillis() + (expireSeconds*1000);

        StringBuilder sb = new StringBuilder("v1")
                .append(ServerAuthUtil.AUTH_SPLIT).append(id)
                .append(ServerAuthUtil.AUTH_SPLIT).append(type)
                .append(ServerAuthUtil.AUTH_SPLIT).append(expireTime)
                .append(ServerAuthUtil.AUTH_SPLIT).append(deviceId);

        return sb.toString();
    }

    public static boolean checkAuthrization(String authKey, long timestamp){
        long currentTime = System.currentTimeMillis();
        if(Math.abs(currentTime - timestamp) > expire_ms ){
            return false;
        }
        String str = generatorToken(timestamp);
        return str.equals(authKey);
    }


    public static class TokenBean {
        private User user;
        private String deviceId;

        public TokenBean(User user, String deviceId) {
            this.user = user;
            this.deviceId = deviceId;
        }

        public User getUser() {
            return user;
        }

        public String getDeviceId() {
            return deviceId;
        }
    }
}
