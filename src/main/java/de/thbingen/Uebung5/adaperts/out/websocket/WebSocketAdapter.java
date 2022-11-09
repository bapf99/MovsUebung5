package de.thbingen.Uebung5.adaperts.out.websocket;

import de.thbingen.Uebung5.ports.in.User;
import de.thbingen.Uebung5.ports.out.Operation;
import de.thbingen.Uebung5.ports.out.UpdateNotificationPort;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("WebSocketText")
public class WebSocketAdapter extends TextWebSocketHandler implements UpdateNotificationPort {

    List<WebSocketSession> sessionList = new ArrayList<>();

    public void afterConnectionEstablished(WebSocketSession session) {
        sessionList.add(session);
        System.out.println("User created");
    }

    @Override
    public void notify(User user, Operation operation) {
        for (WebSocketSession session : sessionList) {
            try {
                session.sendMessage(new TextMessage(user.toString()));
            } catch (IOException e) {
                sessionList.remove(session);
            }
        }
    }
}
