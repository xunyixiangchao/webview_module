package com.lis.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.lis.base.BaseApplication;
import com.lis.webview.IMainProcessToWebViewProcessInterface;
import com.lis.webview.IWebViewProcessToMainAidlInterface;
import com.lis.webview.webviewprocess.mainprocess.MainProcessCommandService;

import java.util.Date;

public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private static volatile WebViewProcessCommandDispatcher instance;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (instance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                if (instance == null) {
                    instance = new WebViewProcessCommandDispatcher();
                }
            }
        }
        return instance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    IWebViewProcessToMainAidlInterface iWebViewProcessToMainAidlInterface;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebViewProcessToMainAidlInterface = IWebViewProcessToMainAidlInterface.Stub.asInterface(service);


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebViewProcessToMainAidlInterface=null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebViewProcessToMainAidlInterface=null;
        initAidlConnection();
    }
    public void executeCommand(String commandName,String params){
        if(iWebViewProcessToMainAidlInterface!=null){
            try {
                iWebViewProcessToMainAidlInterface.handleWebCommand(commandName, params, new IMainProcessToWebViewProcessInterface.Stub() {
                    @Override
                    public void onResult(String callbackName, String response) throws RemoteException {

                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
