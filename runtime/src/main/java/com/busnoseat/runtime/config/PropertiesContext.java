/**
 * 
 * 厚本金融
 * Copyright (c) 2011-2016 Hang Tian Wealth Co.Ltd. All Rights Reserved.
 */
package com.busnoseat.runtime.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @Description: 
 *
 * @author liheng
 * @version $Id: PropertiesContext.java, v 0.1 2016年3月13日 上午11:05:48 liheng Exp $
 */
public class PropertiesContext {
    private static Map<String, String> propertiesMap = new ConcurrentHashMap<String, String>();

    public static String get(String key) {
        return propertiesMap.get(key);
    }

    public static void put(String key, String value) {
        propertiesMap.put(key, value);
    }

    public static void remove(String key) {
        propertiesMap.remove(key);
    }

    public static Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    public static void putAll(Map<String, String> map) {
        propertiesMap.putAll(map);
    }
}
