package com.lis.webview_module;

import com.kingja.loadsir.core.LoadSir;
import com.lis.base.BaseApplication;
import com.lis.base.loadsir.CustomCallback;
import com.lis.base.loadsir.EmptyCallback;
import com.lis.base.loadsir.ErrorCallback;
import com.lis.base.loadsir.LoadingCallback;
import com.lis.base.loadsir.TimeoutCallback;

public class WebViewApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
