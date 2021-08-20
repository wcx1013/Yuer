package com.yd.ye.main.biz;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.CiyBean;
import com.xzq.module_base.bean.ZyBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.ZyAdapter;
import com.yd.ye.main.music.IService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WananggActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bars)
    Toolbar bars;
    @BindView(R.id.tlist)
    RecyclerView wananlist;
    @BindView(R.id.sd)
    ImageView sd;
    //    @BindView(R.id.seekBar)
//    SeekBar seekBar;
    @BindView(R.id.zts)
    ImageView zts;
    @BindView(R.id.fangs)
    ImageView fangs;
    @BindView(R.id.hu)
    TextView hu;

    private ArrayList<CiyBean.ListBean> listwanBeans;
    private ZyAdapter zyAdapter;
    private IService iService;
    private static SeekBar sb_progress;
    private String mFilePath;
    private MediaPlayer mediaPlayer;
    private Boolean isPaused = false;
    private Boolean wcx = false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_an;
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
        Wananrefit();
    }

    private void Wananrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        GridLayoutManager layoutManager = new GridLayoutManager(me, 2);
        wananlist.setLayoutManager(layoutManager);
        wananlist.setNestedScrollingEnabled(false);
        listwanBeans = new ArrayList<>();
        zyAdapter = new ZyAdapter(listwanBeans, this);
        wananlist.setAdapter(zyAdapter);

        ZyBean zyBean = new ZyBean();
        zyBean.setPageNum(1);
        zyBean.setPageSize(30);
        ZyBean.ParamBean paramBean = new ZyBean.ParamBean();
        paramBean.setType("227");
        zyBean.setParam(paramBean);

        Call<CiyBean> ciyBeanCall = RetrofitUtils.getretrofit().getmfList(result, BaseJsonParam.create(JsonParamUtils.getcy(zyBean)));
        ciyBeanCall.enqueue(new Callback<CiyBean>() {
            @Override
            public void onResponse(Call<CiyBean> call, Response<CiyBean> response) {
                List<CiyBean.ListBean> list = response.body().getList();

                if (list != null) {
                    if (list.size() != 0) {
                        listwanBeans.clear();
                        listwanBeans.addAll(list);
                        zyAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<CiyBean> call, Throwable t) {

            }
        });
        zyAdapter.setJiekouhuidiaos(new ZyAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                mFilePath = listwanBeans.get(position).getContent();
                String name = listwanBeans.get(position).getName();
                if (isPaused == true || wcx == true) {
                    mediaPlayer.stop();

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    hu.setText(name);
                } else {

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    hu.setText(name);
                    wcx = true;
                }
            }
        });

    }

    @OnClick({R.id.zts, R.id.fangs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zts:
                zts.setVisibility(View.GONE);
                fangs.setVisibility(View.VISIBLE);
                if (isPaused == true) {
                    mediaPlayer.start();
                } else {
                    playOnlineSound(mFilePath);
                }
                break;
            case R.id.fangs:
                zts.setVisibility(View.VISIBLE);
                fangs.setVisibility(View.GONE);

                if (mediaPlayer.isPlaying()) {
                    wcx = true;
                    mediaPlayer.pause();
                    isPaused = true;
                }
                break;
        }
    }

    //音频播放
    private void playOnlineSound(String soundUrlDict) {//
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(soundUrlDict);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mp != null) {
                        mp.release();
                    }
                    //  Log.d(TAG, "onCompletion: play sound.");
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    // Log.d(TAG, "Play online sound onError: " + i + ", " + i1);
                    return false;
                }
            });
        } catch (IOException e1) {
            // Log.e(TAG, "url: ", e1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(wcx==false){

        }else {
            mediaPlayer.stop();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }
}
