package com.qingqing.test.controller.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zhujianxing on 2019/7/19.
 */
@ServerEndpoint(value = "/websocket/ip_up")
public class UpIpWebSocket {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<Session> webSocketSet = new CopyOnWriteArraySet<Session>();

    @OnOpen
    public void onOpen(Session session) {
        webSocketSet.add(session);     //加入set中
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        sendInfo(message);
    }

    /**
     * 发生错误时调用
     @OnError
     **/
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) {
        for (Session item : webSocketSet) {
            try {
                item.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                continue;
            }
        }
    }
}
