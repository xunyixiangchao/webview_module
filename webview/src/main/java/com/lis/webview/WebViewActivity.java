package com.lis.webview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lis.webview.databinding.ActivityWebviewBinding;
import com.lis.webview.utils.WebViewConstants;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        //设置打开js
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(getIntent().getStringExtra(WebViewConstants.URL));
        mBinding.title.setText(getIntent().getStringExtra(WebViewConstants.TITLE));
        boolean showActionBar = getIntent().getBooleanExtra(WebViewConstants.SHOWACTIONBAR, true);
        if (showActionBar) {
            mBinding.actionBar.setVisibility(View.VISIBLE);
        } else {
            mBinding.actionBar.setVisibility(View.GONE);
        }
    }


}
