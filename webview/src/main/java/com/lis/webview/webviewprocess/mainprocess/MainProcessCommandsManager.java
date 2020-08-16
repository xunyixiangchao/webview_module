package com.lis.webview.webviewprocess.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lis.webview.IMainProcessToWebViewProcessInterface;
import com.lis.webview.IWebViewProcessToMainAidlInterface;
import com.lis.webview.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandsManager extends IWebViewProcessToMainAidlInterface.Stub {
    private volatile static MainProcessCommandsManager sInstance;
    Map<String, Command> mCommandMap = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                if (sInstance == null) {
                    sInstance = new MainProcessCommandsManager();
                }
            }
        }
        return sInstance;
    }

    public MainProcessCommandsManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommandMap.containsKey(command.name())) {
                mCommandMap.put(command.name(), command);
            }
        }
    }

    public void executeCommand(String commandName, Map params, IMainProcessToWebViewProcessInterface callback) {
        Command command = mCommandMap.get(commandName);
        if (command != null) {
            command.execute(params,callback);
        }
    }


    @Override
    public void handleWebCommand(String commandName, String jsonParams, IMainProcessToWebViewProcessInterface callback) throws RemoteException {
        MainProcessCommandsManager.getInstance().executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class),callback);
    }
}
