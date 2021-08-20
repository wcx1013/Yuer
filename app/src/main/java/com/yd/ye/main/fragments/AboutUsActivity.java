package com.yd.ye.main.fragments;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.yd.ye.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class AboutUsActivity extends BasePresenterActivity {
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
    }
    @OnClick(R.id.fan)
    public void onClick() {
        finish();
    }
}
