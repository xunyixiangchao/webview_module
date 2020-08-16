package com.lis.webview_module;

import com.google.auto.service.AutoService;
import com.lis.base.autoservice.ServiceLoaderUtil;
import com.lis.common.autoservice.IUserCenterService;
import com.lis.webview.IMainProcessToWebViewProcessInterface;
import com.lis.webview.command.Command;

import java.util.Map;
import java.util.ServiceLoader;

@AutoService(Command.class)
public class CommandLogin implements Command {
    IUserCenterService mIUserCenterService = ServiceLoaderUtil.load(IUserCenterService.class);
    ;

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, IMainProcessToWebViewProcessInterface callback) {
        if (mIUserCenterService != null) {
            if (!mIUserCenterService.isLogined()) {
                mIUserCenterService.login();
            }

        }

    }
}
