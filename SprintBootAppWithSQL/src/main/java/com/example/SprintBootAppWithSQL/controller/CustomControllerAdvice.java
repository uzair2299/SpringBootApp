package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.config.PropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomControllerAdvice {
    @Autowired
    PropService propService;

//    @RequestMapping("/change-url")
//    public void changeBaseUrl(@RequestBody String newBaseUrl) {
//        pepv3Properties.setBaseUrl(newBaseUrl);
//    }
}
