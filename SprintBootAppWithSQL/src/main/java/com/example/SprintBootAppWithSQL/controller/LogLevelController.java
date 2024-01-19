package com.example.SprintBootAppWithSQL.controller;

import ch.qos.logback.classic.Level;
import com.example.SprintBootAppWithSQL.dto.ChangeLogLevelRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class LogLevelController {

    @PostMapping("/api/v1/changeLogLevel")
    public ResponseEntity<String> changeLogLevel(@RequestBody ChangeLogLevelRequest request) {
        String level = request.getLevel().toUpperCase(Locale.ROOT);
        Level logLevel = Level.toLevel(level);

        switch (level) {
            case "DEBUG":
            case "INFO":
            case "ERROR":
            case "WARN":
                logLevel = Level.toLevel(level);
                return ResponseEntity.ok("Log level changed to: " + logLevel.toString());
            default:
                return ResponseEntity.status(400).body("Invalid log level: " + level);
        }
    }
}
