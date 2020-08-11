package com.lis.webview.webviewclient;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lis.webview.WebViewCallBack;

public class MyWebViewClient extends WebViewClient {
    private WebViewCallBack mWebViewCallBack;

    public MyWebViewClient(WebViewCallBack callBack) {
        mWebViewCallBack = callBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.pageStarted(url);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.pageFinished(url);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mWebViewCallBack != null) {
            mWebViewCallBack.onReceivedError();
        }

    }
}
