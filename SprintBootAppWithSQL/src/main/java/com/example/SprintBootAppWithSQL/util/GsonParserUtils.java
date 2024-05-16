package com.example.SprintBootAppWithSQL.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static java.lang.reflect.Modifier.TRANSIENT;

public class GsonParserUtils {
    private GsonParserUtils() {
    }

    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    public static String convertPojo(Object obj, Class type) {
        return gson.toJson(obj, type);
    }

//    public static String parseObjectToString(Object object){
//        final Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .excludeFieldsWithModifiers(TRANSIENT)
//                .create();
//        return gson.toJson(object);
//    }

    public static <T> T parseStringToObject(String json, Class<T> classObject){
        try{
            return new Gson().fromJson(json, classObject);
        }catch (Exception e){
            return null;
        }
    }
}
