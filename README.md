# 最近项目中一直使用的网络请求框架 OkGo 地址 https://github.com/jeasonlzy/okhttp-OkGo

个人用起来比较顺手，针对目前项目遇到的一些通常请求做了一些封装 (只是一些简单的分装，如果你项目中用到进度条，其实也可以重写下 onStart 和 onFinish)

OkGoUtils 和 FastJsonUtils 这两个工具类，可以减少一些代码量。代码如下:
（我基本都是使用的Stringcallback，然后再配合FastJson解析，具体情况具体处理，能满足我绝大部分需求）

OkGoUtils.java（这边默认使用的是POST请求，其他方式自行更换）

package com.example.admin.realchen;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


import java.util.HashMap;

/**
 * Author:    ChenZheng
 * Date:      2017/12/05 15:11
 * Copyrights: 408280277@qq.com
 * Description: OKGo的工具类
 */
public class OkGoUtils {

    public static void excute(String url, HashMap<String, String> params, final int code, final StrCallback callback) {
        OkGo.<String>post(url)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String rsp = response.body();
                        if (FastJsonUtils.isJSon(rsp)) {
                            if (FastJsonUtils.getResult(rsp)) {
                                callback.requestOk();
                            } else {
                                callback.requestError();
                            }
                        } else {
                            ToastUtils.showLong("数据格式异常");
                        }
                    }
                });
    }
}
FastJsonUtils.java

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
此外还需定义一个接口

package com.example.admin.realchen;


/**
 * Author:    ChenZheng
 * Date:      2017/4/26 14:48
 * Copyrights: 408280277@qq.com
 * Description: 回调接口
 */
public interface StrCallback {

    void requestOk();

    void requestError();

}

简单的使用

HashMap<String, String> params = new HashMap<>();
        params.put("action", "UserLogin");
        params.put("username", "111");
        params.put("password", "111");

        OkGoUtils.excute("http://10.18.13.47:8093/tools/Interface.ashx", params, 1, new StrCallback() {
            @Override
            public void requestOk() {
                LogUtils.d("成功");
            }

            @Override
            public void requestError() {
                LogUtils.d("失败");
            }
        });
欢迎指正！
