package com.yd.ye.main.biz;

import android.os.Bundle;

import com.xzq.module_base.base.BasePresenterActivity;
import com.yd.ye.R;

import org.jetbrains.annotations.Nullable;

public class JlgaiActivity extends BasePresenterActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiu;
    }

    @Override
    protected void initViews(@androidx.annotation.Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        String id = getIntent().getStringExtra("id");

    }
}
