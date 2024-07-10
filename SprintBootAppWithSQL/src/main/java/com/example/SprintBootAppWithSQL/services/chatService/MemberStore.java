package com.example.SprintBootAppWithSQL.services.chatService;

import com.example.SprintBootAppWithSQL.entities.chat.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MemberStore {

    private HashMap<String, User> userHashMap = new HashMap<>();

    public void addUser(User user) {
        userHashMap.put(user.getUserId(), user);
    }

}
