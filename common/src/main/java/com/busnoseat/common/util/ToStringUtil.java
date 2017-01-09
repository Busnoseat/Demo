package com.busnoseat.common.util;

/**
 * The type To string util.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class ToStringUtil {
    /**
     * To string string.
     *
     * @param model the model
     * @return the string
     */
    public static String toString(Object model) {
        return JsonUtil.toJson(model);
    }
}
