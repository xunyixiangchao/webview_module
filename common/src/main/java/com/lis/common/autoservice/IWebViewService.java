package com.lis.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IWebViewService {
    void startWebViewActivity(Context context,String url,String title,boolean isShowActionbar);

    Fragment getWebViewFragment(String url,boolean canNativeRefresh);
}
