package com.yd.ye.main.biz;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.CiyBean;
import com.xzq.module_base.bean.TingBean;
import com.xzq.module_base.bean.TuijianBean;
import com.xzq.module_base.bean.ZyBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.CiAdapter;
import com.yd.ye.main.adapter.TingAdapter;
import com.yd.ye.main.adapter.ZyAdapter;
import com.yd.ye.main.music.IService;

import org.intellij.lang.annotations.JdkConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaiActivity extends BasePresenterActivity {
    @BindView(R.id.tinggg)
    TextView tinggg;
    @BindView(R.id.tinglist)
    RecyclerView tinglist;
    @BindView(R.id.musicgg)
    TextView musicgg;
    @BindView(R.id.ergelist)
    RecyclerView ergelist;
    @BindView(R.id.gushigg)
    TextView gushigg;
    @BindView(R.id.wananlist)
    RecyclerView wananlist;
    @BindView(R.id.sd)
    ImageView sd;
    @BindView(R.id.zts)
    ImageView zts;
    @BindView(R.id.fangs)
    ImageView fangs;
    @BindView(R.id.ha)
    TextView ha;
    //    @BindView(R.id.seekBar)
//    SeekBar sb_progress;
    private ArrayList<TingBean.ListBean> listBeans;
    private TingAdapter tingAdapter;
    private ArrayList<CiyBean.ListBean> listmusicBeans;
    private CiAdapter ciAdapter;
    private ArrayList<CiyBean.ListBean> listwanBeans;
    private ZyAdapter zyAdapter;
    private IService iService;
    private static SeekBar sb_progress;
    private String mFilePath;
    private MediaPlayer mediaPlayer;
    private Boolean isPaused = false;
    private Boolean wcx = false;
    private String musiccontent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_taijiao;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();

        Tingrefit();
        Musicfrefit();
        Wananrefit();
        if(isPaused==true){
            zts.setVisibility(View.GONE);
            fangs.setVisibility(View.VISIBLE);
        }

    }



    private void Wananrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        wananlist.setLayoutManager(layoutManager);
        listwanBeans = new ArrayList<>();
        zyAdapter = new ZyAdapter(listwanBeans, this);
        wananlist.setAdapter(zyAdapter);
        ZyBean zyBean = new ZyBean();
        zyBean.setPageNum(1);
        zyBean.setPageSize(3);
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
                    ha.setText(name);
                } else {

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    ha.setText(name);
                    wcx=true;

                }
            }
        });

    }

    private void Musicfrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        GridLayoutManager layoutManager = new GridLayoutManager(me, 2);
        ergelist.setLayoutManager(layoutManager);
        ergelist.setNestedScrollingEnabled(false);
        listmusicBeans = new ArrayList<>();
        ciAdapter = new CiAdapter(listmusicBeans, this);
        ergelist.setAdapter(ciAdapter);

        ZyBean zyBean = new ZyBean();
        zyBean.setPageNum(1);
        zyBean.setPageSize(6);
        ZyBean.ParamBean paramBean = new ZyBean.ParamBean();
        paramBean.setType("226");
        zyBean.setParam(paramBean);

        Call<CiyBean> ciyBeanCall = RetrofitUtils.getretrofit().getmfList(result, BaseJsonParam.create(JsonParamUtils.getcy(zyBean)));
        ciyBeanCall.enqueue(new Callback<CiyBean>() {
            @Override
            public void onResponse(Call<CiyBean> call, Response<CiyBean> response) {
                List<CiyBean.ListBean> list = response.body().getList();

                if (list != null) {
                    if (list.size() != 0) {
                        listmusicBeans.clear();
                        listmusicBeans.addAll(list);
                        ciAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<CiyBean> call, Throwable t) {

            }
        });
        ciAdapter.setJiekouhuidiaos(new CiAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                mFilePath = listmusicBeans.get(position).getContent();
                String name = listmusicBeans.get(position).getName();
                if (isPaused == true || wcx == true) {
                    mediaPlayer.stop();

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    ha.setText(name);
                } else {

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    ha.setText(name);
                    wcx=true;
                }

            }
        });
    }

    private void Tingrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tinglist.setLayoutManager(layoutManager);
        listBeans = new ArrayList<>();
        tingAdapter = new TingAdapter(listBeans, this);
        tinglist.setAdapter(tingAdapter);

        TuijianBean tuijianBean = new TuijianBean();
        tuijianBean.setPageNum(1);
        tuijianBean.setPageSize(4);

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
                    ha.setText(name);
                } else {

                    playOnlineSound(mFilePath);
                    fangs.setVisibility(View.VISIBLE);
                    ha.setText(name);
                    wcx=true;
                }


            }
        });
    }


    @OnClick({R.id.tinggg, R.id.musicgg, R.id.gushigg, R.id.zts, R.id.fangs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tinggg:
                Intent intent = new Intent(this, TingggActivity.class);
                startActivity(intent);
                break;
            case R.id.musicgg:
                Intent intent1 = new Intent(this, MusicggActivity.class);
                startActivity(intent1);
                break;
            case R.id.gushigg:
                Intent intent2 = new Intent(this, WananggActivity.class);
                startActivity(intent2);
                break;
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

    //读写权限
    public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }


    //音频播放
    private void playOnlineSound(String soundUrlDict) {//
        try {
            mediaPlayer = new MediaPlayer();

          //  mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//音量

            mediaPlayer.setDataSource(soundUrlDict);
            mediaPlayer.prepareAsync();


           // mediaPlayer.setVolume(1f, 1f);//音量
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

    @Override//对系统音量进行监听的方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }




}
