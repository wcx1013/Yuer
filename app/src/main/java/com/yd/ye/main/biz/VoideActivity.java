package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gyf.immersionbar.ImmersionBar;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.utils.GlideUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.RetrofitUtils;
import com.xzq.module_base.views.MyVideoPlayer;
import com.yd.ye.R;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cn.jzvd.Jzvd.SCREEN_NORMAL;

public class VoideActivity extends BasePresenterActivity {
    @BindView(R.id.jz_video)
    MyVideoPlayer mJzvdStd;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.shous)
    ImageView shous;
    private String id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        String url = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");
        hideToolbar();
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
        layoutParams.height = ImmersionBar.getStatusBarHeight(this);
        mView.setLayoutParams(layoutParams);

        mJzvdStd.setUp(url, "", SCREEN_NORMAL);
        ImageView posterImageView = mJzvdStd.posterImageView;
        posterImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        GlideUtils.loadImage(posterImageView, url);
        mJzvdStd.startVideo();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.goOnPlayOnPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Jzvd.goOnPlayOnResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Jzvd.releaseAllVideos();
    }



    @OnClick({R.id.shou, R.id.shous,R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shou:
                shou.setVisibility(View.GONE);
                shous.setVisibility(View.VISIBLE);
                sfscrefit(1);

                break;
            case R.id.shous:
                shous.setVisibility(View.GONE);
                shou.setVisibility(View.VISIBLE);
                sfscrefit(2);
                MyToast.show("取消收藏");
                break;
            case R.id.iv_back:
                finish();
                //onBackClick();
//                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
//                intent.putExtra("data","refresh");
//                LocalBroadcastManager.getInstance(VoideActivity.this).sendBroadcast(intent);
//                sendBroadcast(intent);
                break;
        }
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
