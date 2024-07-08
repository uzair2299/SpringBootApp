package com.example.SprintBootAppWithSQL.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        // Simulate processing delay
        log.info("uzair" + message);
        return "Hello, " + message + "!";
    }
}

