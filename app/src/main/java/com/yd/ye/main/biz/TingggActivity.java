package com.yd.ye.main.biz;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.TingBean;
import com.xzq.module_base.bean.TuijianBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.TingAdapter;

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

public class TingggActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bars)
    Toolbar bars;
    @BindView(R.id.tlist)
    RecyclerView tinglist;
    @BindView(R.id.sd)
    ImageView sd;
    //    @BindView(R.id.seekBar)
//    SeekBar seekBar;
    @BindView(R.id.zts)
    ImageView zts;
    @BindView(R.id.fangs)
    ImageView fangs;
    @BindView(R.id.he)
    TextView he;
    private ArrayList<TingBean.ListBean> listBeans;
    private TingAdapter tingAdapter;

    private String mFilePath;
    private MediaPlayer mediaPlayer;
    private Boolean isPaused = false;
    private Boolean wcx = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ting;
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
        Tingrefit();
    }


    private void Tingrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        GridLayoutManager layoutManager = new GridLayoutManager(me, 3);
        tinglist.setLayoutManager(layoutManager);
        tinglist.setNestedScrollingEnabled(false);
        listBeans = new ArrayList<>();
        tingAdapter = new TingAdapter(listBeans, this);
        tinglist.setAdapter(tingAdapter);

        TuijianBean tuijianBean = new TuijianBean();
        tuijianBean.setPageNum(1);
        tuijianBean.setPageSize(30);

        TuijianBean.ParamBean paramBean = new TuijianBean.ParamBean();
        paramBean.setResType("228");
        tuijianBean.setParam(paramBean);
        Call<TingBean> tingBeanCall = RetrofitUtils.getretrofit().getmflikeList(result, BaseJsonParam.create(JsonParamUtils.mftuijianjson(tuijianBean)));
        tingBeanCall.enqueue(new Callback<TingBean>() {
            @Override
            public void onResponse(Call<TingBean> call, Response<TingBean> response) {
                List<TingBean.ListBean> list = response.body().getList();
                if (list != null) {
                    if (list.size() != 0) {
                        listBeans.clear();
                        listBeans.addAll(list);
                        tingAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<TingBean> call, Throwable t) {

            }
        });
        tingAdapter.setJiekouhuidiaos(new TingAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                mFilePath = listBeans.get(position).getContent();
                String name = listBeans.get(position).getName();
                if (isPaused == true || wcx == true) {
                    mediaPlayer.stop();

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    he.setText(name);
                } else {

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    he.setText(name);
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
    private void playOnlineSound(String soundUrlDict) {
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
