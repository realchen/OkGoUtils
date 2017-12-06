package com.example.admin.realchen;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;

/**
 * Author:    ChenZheng
 * Date:      2017/12/5
 * Copyrights: 江苏中天科技软件技术有限公司
 * Description:
 */
public class MyAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        Utils.init(this);
    }
}
