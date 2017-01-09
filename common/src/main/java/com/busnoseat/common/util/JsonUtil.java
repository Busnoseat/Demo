package com.busnoseat.common.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * The type Json util.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class JsonUtil {

    private static Gson gson = new Gson();

    /**
     *  json转化成 java bean
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param type the type
     * @return java bean of beanClass
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T toObject(String json, Type type) {

        return (T) gson.fromJson(json, type);
    }

    /**
     *  json转化成 java bean
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param beanClass the bean class
     * @return java bean of beanClass
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T toObject(String json, Class beanClass) {
        return (T) gson.fromJson(json, beanClass);
    }

    /**
     * java bean 转json
     *
     * @param object java bean
     * @return json string
     */
    public static String toJson(Object object) {
        return gson.toJson(object);

    }

    public static Map<String, String> toMap(Object object) {
        JsonElement element = gson.toJsonTree(object);
        return gson.fromJson(element, new TypeToken<Map<String, String>>() {
        }.getType());
    }
}
