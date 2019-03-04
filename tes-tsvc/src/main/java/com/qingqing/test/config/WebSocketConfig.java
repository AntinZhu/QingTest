package com.qingqing.test.config;

import com.qingqing.test.controller.MyWebSocket;
import com.qingqing.test.manager.PhoneNumberManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by zhujianxing on 2019/3/3.
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public MyWebSocket myWebSocket(PhoneNumberManager manager){
        MyWebSocket socket = new MyWebSocket();
        socket.setPhoneNumberManager(manager);

        return socket;
    }
}
