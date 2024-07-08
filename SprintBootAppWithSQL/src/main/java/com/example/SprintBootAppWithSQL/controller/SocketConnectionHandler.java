package com.example.SprintBootAppWithSQL.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Socket-Connection Configuration class
@Slf4j
public class SocketConnectionHandler extends TextWebSocketHandler {

    // In this list all the connections will be stored
    // Then it will be used to broadcast the message
    List<WebSocketSession> webSocketSessions
            = Collections.synchronizedList(new ArrayList<>());

    // This method is executed when client tries to connect
    // to the sockets
    @Override
    public void
    afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        // Logging the connection ID with Connected Message
        log.info(session.getId() + " Connected");
        // Adding the session into the list
        webSocketSessions.add(session);
    }

    // When client disconnect from WebSocket then this
    // method is called
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info(session.getId() + " DisConnected");
        // Removing the connection info from the list
        webSocketSessions.remove(session);
    }

    // It will handle exchanging of message in the network
    // It will have a session info who is sending the
    // message Also the Message object passes as parameter
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("Message received at socket");

        super.handleMessage(session, message);
        ObjectMapper objectMapper = new ObjectMapper();

        //The readTree() method converts a JSON string into a JsonNode tree, which allows you to navigate and manipulate the JSON structure.
        JsonNode jsonNode = objectMapper.readTree(message.getPayload().toString());
        String receivedMessage = jsonNode.get("message").asText();
        System.out.println("Received message: " + receivedMessage);

        // Iterate through the list and pass the message to
        // all the sessions Ignore the session in the list
        // which wants to send the message.
        for (WebSocketSession webSocketSession : webSocketSessions) {
//            if (session == webSocketSession)
//                continue;

            // sendMessage is used to send the message to the session
            //Send the JSON string as a TextMessage
            TextMessage textMessage = new TextMessage(receivedMessage);
            webSocketSession.sendMessage(textMessage);
            System.out.println("Send message to the sessions: " + receivedMessage);
        }
    }
}

//WebSocket is a protocol that provides a full-duplex, bi-directional communication channel over a single, long-lived connection between a client and server.
// This protocol is designed for real-time web applications, such as chat applications, live notifications, and online gaming.
//Once a WebSocket connection is established, you cannot send HTTP headers directly through WebSocket frame
//A WebSocket connection starts with an HTTP GET request but is fundamentally different from a regular HTTP GET call.
//In a Spring Boot application, the WebSocket port is typically the same as the application's HTTP port.
//By default, WebSocket endpoints use the same port as the HTTP server.
//For example, if your Spring Boot application runs on port 8080, the WebSocket server will also be available at ws://localhost:8080/ws-endpoint.

//This is because WebSocket connections are established over the same port as HTTP, which means the WebSocket server runs on the same port as the HTTP server.

//What is the purpose of the WebSocket handshake?
//Answer:
//The WebSocket handshake is the initial HTTP request-response process that upgrades an HTTP connection to a WebSocket connection. It involves sending an Upgrade request header from the client and receiving a 101 Switching Protocols response from the server.

