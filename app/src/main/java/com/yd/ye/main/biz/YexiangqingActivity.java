package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YexiangqingActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bars)
    Toolbar bars;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.shous)
    ImageView shous;
    private String id;
    private String star;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_xq;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        bars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
//                intent.putExtra("data","refresh");
//                LocalBroadcastManager.getInstance(YexiangqingActivity.this).sendBroadcast(intent);
//                sendBroadcast(intent);
            }
        });
        String url = getIntent().getStringExtra("url");

        id = getIntent().getStringExtra("id");
        star = getIntent().getStringExtra("s");
        if (star.equals("0")) {
            shou.setVisibility(View.VISIBLE);
            shous.setVisibility(View.GONE);
        } else if (star.equals("1")) {
            shous.setVisibility(View.VISIBLE);
            shou.setVisibility(View.GONE);
        }
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        // web.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);
        shous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shous.setVisibility(View.GONE);
                shou.setVisibility(View.VISIBLE);
                sfscrefit(2);
                MyToast.show("取消收藏");
            }
        });
    }

    @OnClick(R.id.shou)
    public void onClick() {
        shou.setVisibility(View.GONE);
        shous.setVisibility(View.VISIBLE);
        sfscrefit(1);


    }


    public void sfscrefit(int starType) {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);

        Call<TokenBean> getsfsc = RetrofitUtils.getretrofit().getsfsc(result, id, starType);
        getsfsc.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                TokenBean body = response.body();
                //MyToast.show("已收藏");
            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {

            }
        });
    }



}
