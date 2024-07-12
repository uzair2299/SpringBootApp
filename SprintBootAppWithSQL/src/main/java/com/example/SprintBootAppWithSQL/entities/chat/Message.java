package com.example.SprintBootAppWithSQL.entities.chat;

import lombok.Data;

@Data
public class Message {
    private String userId;
    private String userName;
    private String content;
    private String messageTime;
    private Long timeStamp;
    private String messageType;
}
