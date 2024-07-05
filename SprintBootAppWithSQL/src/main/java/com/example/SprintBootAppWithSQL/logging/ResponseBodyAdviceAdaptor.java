package com.example.SprintBootAppWithSQL.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;


@Slf4j
@ControllerAdvice
public class ResponseBodyAdviceAdaptor implements ResponseBodyAdvice<Object> {


    //Jackson is already included in Spring Boot, so you don’t need to add any additional dependencies for basic functionality.
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        httpServletResponse.getStatus();


        // Convert the response body to JSON using Jackson's ObjectMapper
        try {
            String json = objectMapper.writeValueAsString(object);
            log.info(String.format("ResponseBody [%s]", json));
        } catch (IOException e) {
            log.info("Error converting response body to JSON: " + e.getMessage());
        }

        return object;
    }
}
