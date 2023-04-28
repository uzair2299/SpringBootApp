package com.example.SprintBootAppWithSQL.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class MapperUtil {
    public static <T, U> U mapObject(T source, Class<U> destinationType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, destinationType);
    }

    public static <T, U> List<U> mapList(List<T> sourceList, Class<U> destinationType) {
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<U>>() {}.getType();
        return modelMapper.map(sourceList, listType);
    }
}
