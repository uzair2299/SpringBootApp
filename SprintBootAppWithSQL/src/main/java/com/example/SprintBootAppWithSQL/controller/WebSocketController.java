package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.entities.chat.Message;
import com.example.SprintBootAppWithSQL.entities.chat.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class WebSocketController {


//SimpMessageHeaderAccessor provides access to the headers of the WebSocket message, which can include information like message type, destination, session ID, and custom headers.
//It allows you to interact with the WebSocket session attributes. These attributes are session-specific and can be used to store and retrieve information relevant to a particular user's session
//Access Request Info: Use SimpMessageHeaderAccessor to get details about the incoming WebSocket message, such as the destination or session ID.
//Manage Session Data: Store and retrieve user-specific data within the WebSocket session, enabling stateful communication across multiple messages in the same session.

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionId();
        String destination = headerAccessor.getDestination();

       message.setTimeStamp(System.currentTimeMillis());
        User user = new User();
        if(headerAccessor.getSessionAttributes().get("user")==null){
            log.info("No user found in session, creating a new user.");

            user.setUserId("testing");
            user.setUserName("testing");
            headerAccessor.getSessionAttributes().put("user", user);
        }
        message.setUserId("123");
        message.setUserName("123");
        return message;
    }
}


//SimpMessageType
