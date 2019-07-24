package com.qingqing.test.rabbit;

import com.google.common.collect.Sets;
import com.qingqing.common.async.rabbit.AsyncEventNotifyRabbitMsgEntity;
import com.qingqing.common.domain.AsyncEventType;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.common.Env;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.config.inteceptor.EnvHandlerInteceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/7/24.
 */
@Component
public class AsyncEventRabbitMsgListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(AsyncEventRabbitMsgListener.class);

    private static Set<AsyncEventType> IGNORE_SET = Sets.newHashSet(AsyncEventType.student_homework_status_change_notify_for_ts);

    @Autowired
    private PiClient piClient;

    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String encoding = messageProperties.getContentEncoding();
        if(encoding == null) {
            encoding = "UTF-8";
        }

        try {
            String content = new String(message.getBody(), encoding);

            AsyncEventNotifyRabbitMsgEntity entity = (AsyncEventNotifyRabbitMsgEntity)JsonUtil.getObjectFromJson(content, AsyncEventNotifyRabbitMsgEntity.class);
            Long eventId = entity.getBody().getEvent_id();
            Integer eventType = entity.getBody().getEvent_type();

            EnvHandlerInteceptor.setEnv(Env.pfm);
            AsyncEventType asyncEventType = AsyncEventType.valueOf(eventType);
            if(asyncEventType.getSaveInDB() && IGNORE_SET.contains(asyncEventType)){
                piClient.commonRequest("/svc/api/pi/v2/event/clear.json", String.format("{event_id:%d,event_type:%d}", eventId, eventType));
            }
        } catch (UnsupportedEncodingException var6) {
            logger.error("failed to decode mq message:{}", encoding, var6);
        } catch (Exception var7) {
            logger.error("failed to handle mq message:{}", encoding, var7);
        }

    }

    public static void addIgnore(AsyncEventType asyncEventType){
        IGNORE_SET.add(asyncEventType);
    }

    public static void removeIgnore(AsyncEventType asyncEventType){
        IGNORE_SET.remove(asyncEventType);
    }
}
