package com.yd.ye.main.biz;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.yd.ye.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class WebUrlActivity extends BasePresenterActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.fan)
    ImageView fan;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_url;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();

        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());




    }
    @OnClick(R.id.fan)
    public void onClick() {
        finish();
    }
}
