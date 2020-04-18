package ${basePackage}.config;

import com.google.common.base.Joiner;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.StringUtils;
import io.sentry.DefaultSentryClientFactory;
import io.sentry.Sentry;
import io.sentry.SentryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
public class SentryConfig {

    @Bean
    public SentryClient sentryClient(@Value("${"$"}{dsn}")String dsn,
                                     @Value("${"$"}{stacktrace.app.packages:}")String packages,
                                     @Value("${"$"}{environment:}")String environment,
                                     @Value("${"$"}{servername:}")String servername,
                                     @Value("${"$"}{buffer.size:}")String bufferSize,
                                     @Value("${"$"}{async.queuesize:}")String queuesize,
                                     @Value("${"$"}{maxmessagelength:}")String maxmessagelength){
        if ((StringUtils.isEmpty(dsn))) {
            throw new QingQingRuntimeException("sentry dsn is empty");
        }
        Map<String,String> param = new HashMap<>();
        if(!StringUtils.isEmpty(packages)){
            param.put(DefaultSentryClientFactory.IN_APP_FRAMES_OPTION,packages);
        }
        if(!StringUtils.isEmpty(environment)){
            param.put(DefaultSentryClientFactory.ENVIRONMENT_OPTION,environment);
        }
        if(!StringUtils.isEmpty(servername)){
            param.put(DefaultSentryClientFactory.SERVERNAME_OPTION,servername);
        }
        if(!StringUtils.isEmpty(bufferSize)){
            param.put(DefaultSentryClientFactory.BUFFER_SIZE_OPTION,bufferSize);
        }
        if(!StringUtils.isEmpty(queuesize)){
            param.put(DefaultSentryClientFactory.ASYNC_QUEUE_SIZE_OPTION,queuesize);
        }
        if(!StringUtils.isEmpty(maxmessagelength)){
            param.put(DefaultSentryClientFactory.MAX_MESSAGE_LENGTH_OPTION,maxmessagelength);
        }
        String paramUrl = Joiner.on("&").withKeyValueSeparator("=").join(param);
        return Sentry.init(StringUtils.isEmpty(paramUrl) ? dsn : dsn + "?" + paramUrl);
    }
}