package com.lis.webview;

import com.lis.webview.IMainProcessToWebViewProcessInterface;
interface IWebViewProcessToMainAidlInterface {
    void handleWebCommand(String commandName,String jsonParams, in IMainProcessToWebViewProcessInterface callback);
}
