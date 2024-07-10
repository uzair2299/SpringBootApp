package com.example.SprintBootAppWithSQL.eventListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    @EventListener
    public void handleSessionConnectEvent(SessionConnectEvent event) {
        log.info("Session Connect Event: " + event.getMessage().getHeaders());
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        log.info("Session Disconnect Event: " + event.getMessage().getHeaders());
    }
}
