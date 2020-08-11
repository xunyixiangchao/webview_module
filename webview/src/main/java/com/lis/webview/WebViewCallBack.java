package com.lis.webview;

public interface WebViewCallBack {
    void pageStarted(String url);

    void pageFinished(String url);
    void onReceivedError();

    void updateTitle(String title);
}
