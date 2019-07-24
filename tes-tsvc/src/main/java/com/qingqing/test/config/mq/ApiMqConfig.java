package com.qingqing.test.config.mq;

import com.qingqing.common.rabbit.MqConfigHelper;
import com.qingqing.common.rabbit.MqTopicQueueListenBundleBuilder;
import com.qingqing.common.rabbit.MqTopicQueueListenBundleBuilder.MqTopicQueueListenBundle;
import com.qingqing.common.rabbit.params.MqConfigParam;
import com.qingqing.common.rabbit.params.MqListenParam;
import com.qingqing.test.rabbit.AsyncEventRabbitMsgListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangzirong on 8/15/2017.
 */
@Configuration
public class ApiMqConfig {

    private final static String CONNECTION_FACTORY = "apiConnectionFactory";

    @Bean
    public MqConfigParam mqConfigParam(
            @Value("${rabbitmq.pfm.url}") String url,
            @Value("${rabbitmq.pfm.username}") String userName,
            @Value("${rabbitmq.pfm.password}") String password
    ){
        MqConfigParam result = new MqConfigParam();
        result.setUrl(url);
        result.setUsername(userName);
        result.setPasspword(password);

        return result;
    }

    @Bean
    public AbstractMessageListenerContainer asyncEventListenerContainer(
            @Value("${rabbitmq.pfm.async.queue}") String queue,
            @Value("${rabbitmq.pfm.async.exchange}") String exchange,
            AsyncEventRabbitMsgListener messListener,
            MqConfigParam apiMqConfigParam,
            @Value("${rabbitmq.pfm.async.vhost}") String virtualHost) {
        ConnectionFactory connectFactory = MqConfigHelper.newCachingConnectionFactory(apiMqConfigParam, virtualHost);

        MqListenParam listenParam = new MqListenParam();
        listenParam.setQueueName(queue);
        listenParam.setTopicExchange(exchange);
        listenParam.setRoutingKey("api_async_event_v1.#");

        MqTopicQueueListenBundleBuilder builder = new MqTopicQueueListenBundleBuilder(
                listenParam, messListener, connectFactory
        );
        builder.setListenConcurrentConsumers(5);
        MqTopicQueueListenBundle bundle = builder.initBundle();
        return bundle.getListenerContainer();
    }
}