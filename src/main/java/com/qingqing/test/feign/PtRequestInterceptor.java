package com.qingqing.test.feign;

import com.google.common.collect.Maps;
import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.domain.UserSession;
import com.qingqing.test.service.SessionRedisService;
import com.qingqing.test.service.UserSessionService;
import com.qingqing.test.util.ServerAuthUtil;
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

    private Map<UserType, String> userTypeKeyMap;

    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private SessionRedisService sessionRedisService;

    public PtRequestInterceptor(){
        userTypeKeyMap = Maps.newHashMap();
        userTypeKeyMap.put(UserType.student, STUDENT_ID);
        userTypeKeyMap.put(UserType.teacher, TEACHER_ID);
        userTypeKeyMap.put(UserType.ta, TA_ID);
    }

    @Override
    protected void doApply(RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();

        for(Entry<UserType, String> entry : userTypeKeyMap.entrySet()){
            Collection<String> headerValues = headers.get(entry.getValue());
            if(!CollectionsUtil.isNullOrEmpty(headerValues)){
                Long needSessionUserId = Long.parseLong(headerValues.iterator().next());
                initSessionHeader(template, entry.getKey(), needSessionUserId);
                return;
            }
        }

        throw new QingQingRuntimeException("pt interface need assign user id for session");
    }

    private void initSessionHeader(RequestTemplate template, UserType userType, Long userId){
        template.header(RequestExtract.SESSION, getSession(userType, userId));
        template.header(RequestExtract.KEY_AUTH_TOKEN, ServerAuthUtil.generatorEncryptedToken(new User(userType, userId), 3600L, ""));
    }

    private String getSession(UserType userType, Long userId){
        Integer sessionType = 1;
        User user = new User(userType, userId);

        UserSession session = userSessionService.findUserSession(userId, userType, sessionType);
        if (session == null) {
            Long sessionNew = userSessionService.upsertSession(user, sessionType, "", "");
            sessionRedisService.addSession(user, sessionType, String.valueOf(sessionNew));

            return String.valueOf(sessionNew);
        }

        return String.valueOf(session.getSessionId());
    }
}
