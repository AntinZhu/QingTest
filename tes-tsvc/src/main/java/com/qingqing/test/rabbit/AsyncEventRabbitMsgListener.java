package com.qingqing.test.rabbit;

import com.qingqing.common.async.rabbit.AsyncEventNotifyRabbitMsgEntity;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.common.Env;
import com.qingqing.test.bean.config.ITestConfigNotify;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.config.inteceptor.EnvHandlerInteceptor;
import com.qingqing.test.manager.TestConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/7/24.
 */
@Component
public class AsyncEventRabbitMsgListener implements MessageListener, ITestConfigNotify {
    private static final Logger logger = LoggerFactory.getLogger(AsyncEventRabbitMsgListener.class);
    private static final String CONFIG_KEY = "async_event_auto_ignore_list";

    @Autowired
    private PiClient piClient;
    @Autowired
    private TestConfigManager testConfigManager;
    private static Set<Integer> IGNORE_SET;

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

            if(IGNORE_SET.contains(eventType)){
                piClient.commonRequest("/svc/api/pi/v2/event/clear.json", String.format("{event_id:%d,event_type:%d}", eventId, eventType), "");
            }
        } catch (UnsupportedEncodingException var6) {
            logger.error("failed to decode mq message:{}", encoding, var6);
        } catch (Exception var7) {
            logger.error("failed to handle mq message:{}", encoding, var7);
        }

    }

    public void addIgnore(int asyncEventType){
        IGNORE_SET.add(asyncEventType);
        testConfigManager.addConfig(CONFIG_KEY, JsonUtil.format(IGNORE_SET));
    }

    public void removeIgnore(int asyncEventType){
        IGNORE_SET.remove(asyncEventType);
        testConfigManager.addConfig(CONFIG_KEY, JsonUtil.format(IGNORE_SET));
    }

    @Override
    public void notifyChange() {
        IGNORE_SET = new HashSet<>(JsonUtil.parserJsonList(testConfigManager.getConfigValue(CONFIG_KEY, "[147]"), Integer.class));
    }
}
