package com.lis.webview.webviewprocess.websetting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lis.webview.BuildConfig;

public class WebViewDefaultSettings {
    private volatile static WebViewDefaultSettings setting;
    private WebSettings mWebSettings;

    private WebViewDefaultSettings() {

    }

    public static WebViewDefaultSettings getInstance() {
        return new WebViewDefaultSettings();
    }

    public void setSetting(WebView webView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.enableSlowWholeDocumentDraw();
        }
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(true);
        if (isNetworkConnected(webView.getContext())) {
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebSettings.setTextZoom(100);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setSupportMultipleWindows(true);
        mWebSettings.setBlockNetworkImage(false); //是否阻塞加载网络图片  协议http or https
        mWebSettings.setAllowFileAccess(true);//允许加载本地文件html  file协议
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWebSettings.setAllowFileAccessFromFileURLs(false); //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
            mWebSettings.setAllowUniversalAccessFromFileURLs(false);//允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        mWebSettings.setSavePassword(false);
        mWebSettings.setSaveFormData(false);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setNeedInitialFocus(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebSettings.setDefaultFontSize(16);
        mWebSettings.setMinimumFontSize(10);//设置 WebView 支持的最小字体大小，默认为 8
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setUseWideViewPort(true);

        String appCacheDir = webView.getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        Log.i("WebSetting", "WebView cache dir: " + appCacheDir);
        mWebSettings.setDatabasePath(appCacheDir);
        mWebSettings.setAppCachePath(appCacheDir);
        mWebSettings.setAppCacheMaxSize(1024 * 1024 * 80);

        // 用户可以自己设置useragent
        // mWebSettings.setUserAgentString("webprogress/build you agent info");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }
    }

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectedService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectedService.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnected();
        } else {
            return false;
        }


    }


}
