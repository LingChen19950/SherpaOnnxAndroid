package com.lc.sherpa;

import android.app.Application;

import com.lc.sherpa.utils.SpUtil;
import com.lc.sherpa.utils.Utils;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化工具类
        Utils.init(this);
        // 初始化SharedPreferences，需要在Utils init后面才能调用
        SpUtil.getInstance();
    }

}
