package ${basePackage}.config;

import com.qingqing.common.web.aspect.log.GuidAspect;
import com.qingqing.common.web.aspect.log.MessageListenerGuidAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
public class CommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(CommonConfig.class);

    @Bean
    public GuidAspect guidAspect(){
        return new GuidAspect();
    }

    @Bean
    public MessageListenerGuidAspect messageListenerGuidAspect (){
        MessageListenerGuidAspect messageListenerGuidAspect = new MessageListenerGuidAspect();
        return  messageListenerGuidAspect;
    }
}
