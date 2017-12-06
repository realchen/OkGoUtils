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
                .isSpliceUrl(true)
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
