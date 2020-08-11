package com.lis.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.auto.service.AutoService;
import com.lis.common.autoservice.IWebViewService;
import com.lis.webview.utils.WebViewConstants;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(WebViewConstants.TITLE, title);
            intent.putExtra(WebViewConstants.URL, url);
            intent.putExtra(WebViewConstants.SHOWACTIONBAR, isShowActionBar);
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWebViewFragment(String url,boolean canNativeRefresh) {
        return WebViewFragment.newInstance(url,canNativeRefresh);
    }
}
