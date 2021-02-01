package com.imooc.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket
 *
 * @author yangxin
 * 2019/12/30 16:20
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static final CopyOnWriteArraySet<WebSocket> WEB_SOCKET_SET = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;

        WEB_SOCKET_SET.add(this);
        log.info("【websocket消息】有新的连接，总数：[{}]", WEB_SOCKET_SET.size());
    }

    @OnClose
    public void onClose() {
        WEB_SOCKET_SET.remove(this);
        log.info("【websocket消息】连接断开，总数：[{}]", WEB_SOCKET_SET.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端发来的消息：[{}]", message);
    }

    /**
     * 发送消息（这个好像是广播消息）
     */
    public void sendMessage(String message) {
        for (WebSocket webSocket : WEB_SOCKET_SET) {
            log.info("【websocket消息】广播消息，message=[{}]", message);

            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
