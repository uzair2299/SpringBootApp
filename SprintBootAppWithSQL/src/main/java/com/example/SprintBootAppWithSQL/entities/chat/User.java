package com.example.SprintBootAppWithSQL.entities.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String userId;
    private String userName;
}
