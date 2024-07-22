package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.entities.chat.Message;
import com.example.SprintBootAppWithSQL.entities.chat.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


@Controller
@Slf4j
public class WebSocketController {
    private final Random random = new Random();
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
//SimpMessageHeaderAccessor provides access to the headers of the WebSocket message, which can include information like message type, destination, session ID, and custom headers.
//It allows you to interact with the WebSocket session attributes. These attributes are session-specific and can be used to store and retrieve information relevant to a particular user's session
//Access Request Info: Use SimpMessageHeaderAccessor to get details about the incoming WebSocket message, such as the destination or session ID.
//Manage Session Data: Store and retrieve user-specific data within the WebSocket session, enabling stateful communication across multiple messages in the same session.
//The @Payload annotation in Spring Boot is commonly used in the context of messaging, such as with WebSockets, AMQP (e.g., RabbitMQ), or other messaging systems.
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {

        String sessionId = headerAccessor.getSessionId();
        String destination = headerAccessor.getDestination();

       message.setTimeStamp(System.currentTimeMillis());

        LocalTime now = LocalTime.now();
        // Define the formatter for HH:mm format
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        // Format the time
        String formattedTime = now.format(formatter);
        message.setMessageTime(formattedTime);
        User user = new User();
        if(headerAccessor.getSessionAttributes().get("user")==null){
            log.info("No user found in session, creating a new user.");
            user.setUserId(message.getUserId());
            user.setUserName(message.getUserName());
            headerAccessor.getSessionAttributes().put("user", user);
        }
        return message;
    }

    @Scheduled(fixedRate = 3000)
    public void sendPeriodicMessages() {
        Message message = new Message();
        message.setTimeStamp(System.currentTimeMillis());
        message.setUserId(generateRandomUserId());
        message.setUserName(generateRandomUserName());
        LocalTime now = LocalTime.now();

        // Define the formatter for HH:mm format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        // Format the time
        String formattedTime = now.format(formatter);
        message.setMessageTime(formattedTime);
        message.setContent("message from server after 30 sec" + message.getUserName());

        // Log the message being sent
        log.info("Sending periodic message: {}", message);

        messagingTemplate.convertAndSend("/topic/greetings", message);
    }

    private String generateRandomUserId() {
        return "user-" + random.nextInt(1000); // Generates user IDs like user-123
    }

    private String generateRandomUserName() {
        return "name-" + random.nextInt(1000); // Generates user names like name-123
    }
}


