package com.example.SprintBootAppWithSQL.eventListener;

import com.example.SprintBootAppWithSQL.entities.chat.Message;
import com.example.SprintBootAppWithSQL.entities.chat.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class WebSocketEventListener {


    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @EventListener
    public void handleSessionConnectEvent(SessionConnectEvent event) {
        log.info("Session Connect Event: " + event.getMessage().getHeaders());
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        User user = (User) headerAccessor.getSessionAttributes().get("user");
        if(user !=null){
            LocalTime now = LocalTime.now();
            // Define the formatter for HH:mm format
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            // Format the time
            String formattedTime = now.format(formatter);

            Message message = new Message();
            message.setUserId(user.getUserId());
            message.setContent(String.format("%s left the chat",user.getUserName()));
            message.setMessageTime(formattedTime);
            message.setMessageType(SimpMessageType.DISCONNECT.name());
            log.info("leaving sessionDisconnectedEvent method");
            messagingTemplate.convertAndSend("/topic/greetings", message);
        }
    }
}
