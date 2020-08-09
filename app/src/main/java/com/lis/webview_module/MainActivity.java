package com.lis.webview_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lis.base.autoservice.ServiceLoaderUtil;
import com.lis.common.autoservice.IWebViewService;
import com.lis.webview.WebViewActivity;

import java.util.ServiceLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWebViewService webViewService = ServiceLoaderUtil.load(IWebViewService.class);
                if (webViewService != null) {
                    webViewService.startWebViewActivity(MainActivity.this, "https://www.baidu.com", "百度",false);
                }

                // startActivity(new Intent(MainActivity.this, WebViewActivity.class));

            }
        });
    }
}
