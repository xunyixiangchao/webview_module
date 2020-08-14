package com.lis.webview.webviewprocess.webchromeclient;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.lis.webview.WebViewCallBack;

public class MyWebChromeClient extends WebChromeClient {

    private WebViewCallBack mWebViewCallBack;

    public MyWebChromeClient(WebViewCallBack callBack) {
        mWebViewCallBack = callBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if(mWebViewCallBack!=null){
            mWebViewCallBack.updateTitle(title);
        }
    }
}
