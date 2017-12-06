package com.example.admin.realchen;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;
import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Author:    ChenZheng
 * Date:      2017/4/26 14:48
 * Copyrights: 408280277@qq.com
 * Description: 封装fast常用的一些方法
 */
public class FastJsonUtils {

    public static boolean isJSon(String jsonString) {
        try {
            JSONObject.parse(jsonString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根據key获取value
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static String getValue(String jsonString, String key) {
        return JSONObject.parseObject(jsonString).getString(key);
    }

    /**
     * 获取data
     *
     * @param jsonString
     * @return
     */
    public static String getData(String jsonString) {
        return JSONObject.parseObject(jsonString).getString("data");
    }

    /**
     * 获取data
     *
     * @param jsonString
     * @return
     */
    public static String getList(String jsonString) {
        return JSONObject.parseObject(jsonString).getString("list");
    }

    /**
     * 获取result
     *
     * @param jsonString
     * @return
     */
    public static boolean getResult(String jsonString) {
        return "1".equals(getValue(jsonString, "result"));
    }

    /**
     * 获取错误信息
     *
     * @param jsonString
     * @return
     */
    public static String getErrMsg(String jsonString) {
        return getValue(jsonString, "msg");
    }

    /**
     * 获取对象
     *
     * @param key
     * @param className
     * @param <T>
     * @return
     */
    public static <T> T getModel(String jsonString, String key, Class<T> className) {
        return parseObject(getValue(jsonString, key), className);
    }

    /**
     * 获取数组
     *
     * @param jsonString
     * @param key
     * @param className
     * @param <T>
     * @return
     */
    public static <T> List<T> getModelList(String jsonString, String key, Class<T> className) {
        return JSONArray.parseArray(getValue(jsonString, key), className);
    }

    /**
     * 获取数组
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static List<String> getModelList(String jsonString, String key) {
        return parseArray(getValue(jsonString, key), String.class);
    }
}
