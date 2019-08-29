package web.socket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.sockjs.client.WebSocketClientSockJsSession;
import web.socket.service.CxkService;
import web.socket.utils.HttpClientUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName CustomHandler
 * Package web.socket
 * Description
 *
 * @author gmb1995@outlook.com
 * @date 2019-08-27 20:41
 */
public class ClientMsgHandler extends TextWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientMsgHandler.class);
    private static final List<WebSocketSession> SESSIONS = new ArrayList<>();

    public ClientMsgHandler() {
        super();
    }

    /**
     * 建立连接后触发的回调
     *
     * @param session 当前连接会话
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        SESSIONS.add(session);
        LOGGER.info(session + "，连接建立.....");
    }

    /**
     * 收到信息触发的回调
     *
     * @param session 当前连接会话
     * @param message 收到的消息对象
     * @throws Exception 可能会抛出的异常
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 处理文本消息
     *
     * @param session 本次连接的会话
     * @param message 收到的消息
     * @throws Exception 可能出现的异常
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //2.doGet获得服务端的消息
        LOGGER.info("___________________");
        LOGGER.info("服务端获得消息：" + message.getPayload());
        LOGGER.info("服务端发送消息：" + message.getPayload());
        //确认每一个session都是开者的
        ensureSession();
        for (WebSocketSession webSocketSession : SESSIONS) {
            if (!session.equals(webSocketSession)) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    /**
     * 传输信息出错时触发的回调
     *
     * @param session   当前连接会话
     * @param exception 出现的错误信息
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        LOGGER.info("WebSocket出现错误....");
        exception.printStackTrace();
    }

    /**
     * 断开连接触发的回调
     *
     * @param session 当前连接会话
     * @param status  websocket关闭的状态和原因
     * @throws Exception 可能出现的异常
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("WebSocket断开....");
        session.close();
    }

    private void ensureSession() throws IOException {
        for (WebSocketSession session : SESSIONS) {
            if (!session.isOpen()) {
                SESSIONS.remove(session);
            }
        }
    }
}
