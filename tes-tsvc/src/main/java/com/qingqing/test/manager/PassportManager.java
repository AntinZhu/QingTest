package com.qingqing.test.manager;

import com.qingqing.api.passort.proto.PassportLoginProto.PassportLoginResponse;
import com.qingqing.api.passort.proto.PassportLoginProto.PassportTkLoginRequestV2;
import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.common.web.util.ServerAuthUtil;
import com.qingqing.test.bean.inter.UserRequestParam;
import com.qingqing.test.client.PassportPiClient;
import com.qingqing.test.controller.errorcode.BaseInterfaceErrorCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/1/16.
 */
@Component
public class PassportManager {

    @Autowired
    private PassportPiClient passportPiClient;

    @Test
    public UserRequestParam getToken(Long userId, UserType userType){
        String token;
        String session;
        String qingqingUserId;
        try{
            PassportTkLoginRequestV2 request = PassportTkLoginRequestV2.newBuilder()
                    .setUserType(UserProto.UserType.valueOf(userType.getValue()))
                    .setUserId(userId).build();
            PassportLoginResponse response = passportPiClient.getTokenAndSession(request);
            token = response.getToken();
            session = response.getSessionId();
            qingqingUserId = response.getQingqingUserId();
            if(StringUtils.isEmpty(qingqingUserId)){
                qingqingUserId = UserIdEncoder.encodeUser(userType, userId);
            }
        }
        catch (ErrorCodeException e){
            throw e;
        }catch (Exception e){
            throw new ErrorCodeException(BaseInterfaceErrorCode.get_token_session_fail, "", e);
        }

        long time = System.currentTimeMillis();

        UserRequestParam tokenAndSession = new UserRequestParam();
        tokenAndSession.setSession(session);
        tokenAndSession.setToken(token);
        tokenAndSession.setTimestamp(String.valueOf(time));
        tokenAndSession.setAuthkey(ServerAuthUtil.generatorToken(time));
        tokenAndSession.setQingqingUserId(qingqingUserId);
        return tokenAndSession;
    }
}
