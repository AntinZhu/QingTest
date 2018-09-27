package com.qingqing.test.feign;

import com.google.common.collect.Maps;
import com.qingqing.api.passort.proto.PassportLoginProto.PassportLoginResponse;
import com.qingqing.api.passort.proto.PassportLoginProto.PassportTkLoginRequestV2;
import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.client.PassportPiClient;
import com.qingqing.test.controller.errorcode.BaseInterfaceErrorCode;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by zhujianxing on 2018/2/4.
 */
public class PtRequestInterceptor extends ProtoRequestInterceptor {

    public static final String STUDENT_ID = "studentId";
    public static final String TEACHER_ID = "teacherId";
    public static final String TA_ID = "taId";

    protected Map<UserType, String> userTypeKeyMap;

    public PtRequestInterceptor() {
        userTypeKeyMap = Maps.newHashMap();
        userTypeKeyMap.put(UserType.student, STUDENT_ID);
        userTypeKeyMap.put(UserType.teacher, TEACHER_ID);
        userTypeKeyMap.put(UserType.ta, TA_ID);
    }

    @Autowired
    private PassportPiClient passportPiClient;

    @Override
    protected void doApply(RequestTemplate template) {
        initSession(template);
    }

    private void initSession(RequestTemplate template){
        Map<String, Collection<String>> headers = template.headers();

        for(Entry<UserType, String> entry : userTypeKeyMap.entrySet()){
            Collection<String> headerValues = headers.get(entry.getValue());
            if(!CollectionsUtil.isNullOrEmpty(headerValues)){
                Long needSessionUserId = Long.parseLong(headerValues.iterator().next());
                initSessionHeader(template, entry.getKey(), needSessionUserId);
                return;
            }
        }

        Collection<String> userTypeParams  = headers.get(USER_TYPE);
        Collection<String> userIdParams  = headers.get(USER_ID);
        if(!CollectionsUtil.isNullOrEmpty(userTypeParams) && !CollectionsUtil.isNullOrEmpty(userIdParams)){
            String userId = userIdParams.iterator().next();
            String userType = userTypeParams.iterator().next();
            initSessionHeader(template, UserType.valueOf(userType), Long.valueOf(userId));
            return;
        }

        throw new QingQingRuntimeException("pt interface need assign user id for session");
    }

    private void initSessionHeader(RequestTemplate template, UserType userType, Long userId){
        String token;
        String session;
        try{
            PassportTkLoginRequestV2 request = PassportTkLoginRequestV2.newBuilder()
                    .setUserType(UserProto.UserType.valueOf(userType.getValue()))
                    .setUserId(userId).build();
            PassportLoginResponse response = passportPiClient.getTokenAndSession(request);
            token = response.getToken();
            session = response.getSessionId();
        }catch (Exception e){
            throw new ErrorCodeException(BaseInterfaceErrorCode.get_token_session_fail, "", e);
        }
        template.header(RequestExtract.SESSION, session);
        template.header(RequestExtract.KEY_AUTH_TOKEN, token);
    }

}
