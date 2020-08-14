package com.lis.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lis.webview.WebViewCallBack;
import com.lis.webview.bean.JsParam;
import com.lis.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.lis.webview.webviewprocess.websetting.WebViewDefaultSettings;
import com.lis.webview.webviewprocess.webviewclient.MyWebViewClient;

import java.util.Map;


public class BaseWebView extends WebView {
    public static final String TAG = "BaseWebView";

    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewDefaultSettings.getInstance().setSetting(this);
        addJavascriptInterface(this, "webview");
    }

    public void setWebViewCallBack(WebViewCallBack callBack) {
        setWebChromeClient(new MyWebChromeClient(callBack));
        setWebViewClient(new MyWebViewClient(callBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                if ("showToast".equalsIgnoreCase(jsParamObject.name)) {
                    Toast.makeText(getContext(), String.valueOf(new Gson().fromJson(jsParamObject.param, Map.class).get("message")), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
