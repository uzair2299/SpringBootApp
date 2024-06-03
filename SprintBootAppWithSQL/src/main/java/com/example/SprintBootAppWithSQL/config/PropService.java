package com.example.SprintBootAppWithSQL.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropService {
    @Value("${integration.pepv3.base.url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void updateBaseUrl(String newBaseUrl) {
        this.baseUrl = newBaseUrl;
    }
}
