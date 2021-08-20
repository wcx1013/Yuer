package com.yd.ye.main.biz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.yd.ye.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoztActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bars)
    Toolbar bars;
    @BindView(R.id.nv)
    LinearLayout nv;
    @BindView(R.id.nan)
    LinearLayout nan;
    @BindView(R.id.yun)
    LinearLayout yun;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zt;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            hideToolbar();
            bars.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }



    @OnClick({R.id.nv, R.id.nan, R.id.yun})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nv:
                Intent intent = new Intent(this, BaoxinxActivity.class);
                startActivity(intent);
                break;
            case R.id.nan:
                Intent intent1 = new Intent(this, BaoxinxActivity.class);
                startActivity(intent1);
                break;
            case R.id.yun:
                Intent intent2 = new Intent(this, YucqActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
