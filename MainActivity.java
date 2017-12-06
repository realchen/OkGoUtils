package com.example.admin.realchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.blankj.utilcode.util.LogUtils;

import java.util.HashMap;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doHttp();
    }

    private void doHttp() {
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
    }
}
