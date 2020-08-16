package com.lis.webview_module;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.lis.base.BaseApplication;
import com.lis.webview.IMainProcessToWebViewProcessInterface;
import com.lis.webview.command.Command;

import java.util.Map;

@AutoService(Command.class)
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(final Map parameters, IMainProcessToWebViewProcessInterface callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_LONG).show();
            }
        });
    }
}
