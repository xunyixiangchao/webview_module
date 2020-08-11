package com.lis.webview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebHistoryItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lis.base.loadsir.ErrorCallback;
import com.lis.base.loadsir.LoadingCallback;
import com.lis.webview.databinding.FragmentWebviewBinding;
import com.lis.webview.utils.WebViewConstants;
import com.lis.webview.webchromeclient.MyWebChromeClient;
import com.lis.webview.webviewclient.MyWebViewClient;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {
    private String mUrl;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    private static final String TAG = "WebViewFragment";
    private boolean mIsError;
    private boolean mCanNativeRefresh;

    public static WebViewFragment newInstance(String url, boolean mCanNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WebViewConstants.URL, url);
        bundle.putBoolean(WebViewConstants.CANNATIVEREFRESH, mCanNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(WebViewConstants.URL);
            mCanNativeRefresh = bundle.getBoolean(WebViewConstants.CANNATIVEREFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(mUrl);
        mLoadService = LoadSir.getDefault().register(mBinding.refreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
        mBinding.webview.setWebViewClient(new MyWebViewClient(this));
        mBinding.webview.setWebChromeClient(new MyWebChromeClient(this));
        mBinding.refreshLayout.setEnableLoadMore(false);
        mBinding.refreshLayout.setOnRefreshListener(this);
        mBinding.refreshLayout.setEnableRefresh(mCanNativeRefresh);
        return mLoadService.getLoadLayout();

    }


    @Override
    public void pageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        } else {
            Log.e(TAG, "WebViewCallBack is null");
        }
    }

    @Override
    public void pageFinished(String url) {
        if (mIsError) {
            mBinding.refreshLayout.setEnableRefresh(true);
        } else {
            mBinding.refreshLayout.setEnableRefresh(mCanNativeRefresh);
        }
        Log.d(TAG, "pageFinished");
        mBinding.refreshLayout.finishRefresh();
        if (mLoadService != null) {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                mLoadService.showSuccess();
            }
        } else {
            Log.e(TAG, "WebViewCallBack is null");
        }
        mIsError = false;

    }

    @Override
    public void onReceivedError() {
        Log.e(TAG, "onReceivedError");
        mIsError = true;
        mBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }
}

































