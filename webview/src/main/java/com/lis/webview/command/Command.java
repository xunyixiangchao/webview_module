package com.lis.webview.command;

import com.lis.webview.IMainProcessToWebViewProcessInterface;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map parameters, IMainProcessToWebViewProcessInterface callback);
}
