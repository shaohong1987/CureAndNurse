package com.shaohong.cureandnurse.utils;

import android.app.Application;

/**
 * Created by shaohong on 2017/5/4.
 */

public class ContextUtil extends Application {
    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}