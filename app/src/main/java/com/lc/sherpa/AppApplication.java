package com.lc.sherpa;

import android.app.Application;

import com.lc.sherpa.utils.ModelsJsonManager;
import com.lc.sherpa.utils.Utils;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        //
        ModelsJsonManager.init();
    }

}
