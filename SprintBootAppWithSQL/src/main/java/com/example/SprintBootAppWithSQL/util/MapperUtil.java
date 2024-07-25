package com.example.SprintBootAppWithSQL.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ModelMapper modelMapper = new ModelMapper();
    public static <T, U> U mapObject(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }

//    public static <T, U> List<U> mapList(List<T> sourceList, Class<U> destinationType) {
//        Type listType = new TypeToken<List<U>>() {}.getType();
//        return modelMapper.map(sourceList, listType);
//    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static void map(Object source, Object destination) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(source, destination);
    }

    public static <T> String convertListToJsonString(List<T> list) throws JsonProcessingException {
        return mapper.writeValueAsString(list);
    }

    public static <T> List<T> convertJsonStringToList(String jsonString, TypeReference<List<T>> typeReference) throws IOException, JsonProcessingException {
        return mapper.readValue(jsonString, typeReference);
    }

}
