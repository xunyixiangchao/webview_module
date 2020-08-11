package com.lis.webview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lis.webview.databinding.ActivityWebviewBinding;
import com.lis.webview.utils.WebViewConstants;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        //设置打开js

        mBinding.title.setText(getIntent().getStringExtra(WebViewConstants.TITLE));
        boolean showActionBar = getIntent().getBooleanExtra(WebViewConstants.SHOWACTIONBAR, true);
        if (showActionBar) {
            mBinding.actionBar.setVisibility(View.VISIBLE);
        } else {
            mBinding.actionBar.setVisibility(View.GONE);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(getIntent().getStringExtra(WebViewConstants.URL),
                getIntent().getBooleanExtra(WebViewConstants.CANNATIVEREFRESH, true));
        fragmentTransaction.replace(R.id.web_view_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }

}
