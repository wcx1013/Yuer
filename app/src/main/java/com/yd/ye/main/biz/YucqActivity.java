package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.ChanBean;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.bean.TuppianBean;

import com.xzq.module_base.managers.GlideEngine;
import com.xzq.module_base.managers.LubanManager;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.GlideUtils;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.PermissionUtil;
import com.xzq.module_base.utils.RetrofitUtils;
import com.xzq.module_base.views.DialogClass;
import com.yd.ye.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YucqActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bars)
    Toolbar bars;
    @BindView(R.id.ycqtime)
    TextView ycqtime;
    @BindView(R.id.ghw)
    ImageView ghw;
    @BindView(R.id.qued)
    ImageView qued;
    private String path;
    private String url;
    private CustomDatePicker customDatePicker;
    private String now;
    private String chushengriqi;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ycq;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        DatePicker();
        bars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 显示时间
     */
    private void DatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        //获取当前时间
        now = sdf.format(new Date());
        //tvElectricalTime.setText(now.split(" ")[0]);
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {


            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                Log.d("yyyyy", time);
                chushengriqi = time.split(" ")[0];
                String replace = chushengriqi.replace("-", "");
                ycqtime.setText(replace);

            }
        }, now, "2031-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动
    }


    @OnClick({R.id.ghw, R.id.ycqtime, R.id.qued})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ghw:
                PermissionUtil.requestAlbum(() -> {
                    DialogClass.showDialogPic((parent, view, position, id) -> {
                        switch (position) {
                            case 0:
                                //相机
                                PictureSelector.create(me)
                                        .openCamera(PictureMimeType.ofImage())
                                        .isEnableCrop(true)
                                        .showCropGrid(true)
                                        .withAspectRatio(1, 1)
                                        .forResult(PictureConfig.CHOOSE_REQUEST);
                                break;
                            case 1:
                                //相册
                                PictureSelector.create(me)
                                        .openGallery(PictureMimeType.ofImage())
                                        .imageSpanCount(4)
                                        .selectionMode(PictureConfig.SINGLE)
                                        .showCropGrid(true)
                                        .isEnableCrop(true)
                                        .freeStyleCropEnabled(false)
                                        .imageEngine(GlideEngine.createGlideEngine())
                                        .withAspectRatio(1, 1)
                                        .forResult(PictureConfig.CHOOSE_REQUEST);
                                break;
                        }

                    }, me);
                });
                break;
            case R.id.ycqtime:
                customDatePicker.show(now);
                break;

            case R.id.qued:
                //确定
                if(!ycqtime.getText().toString().isEmpty()){
                    homeEditrefit();
                }else {
                    MyToast.show("请输入完整");
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                if (localMedia.size() > 0) {
                    path = localMedia.get(0).getCutPath();
                    Image(path);
                }
                break;


        }
    }

    private void homeEditrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String tok = token.getString("result", null);
        ChanBean chanBean = new ChanBean();
        ChanBean.ParamBean paramBean = new ChanBean.ParamBean();
        String s = ycqtime.getText().toString();
        int i = Integer.parseInt(s);
        paramBean.setProvince("1");
        paramBean.setBirthDate(i);
       chanBean.setParam(paramBean);
        Call<TokenBean> getedit = RetrofitUtils.getretrofit().getedit(tok, BaseJsonParam.create(JsonParamUtils.getxiu(chanBean)));
        getedit.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                finish();
            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {

            }
        });

    }

    public void Image(String localImgPath) {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String tok = token.getString("result", null);
        LubanManager.compressSingle(localImgPath, result -> {
            for (String p : result) {
                File file = new File(p);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                Call<TuppianBean> image = RetrofitUtils.getretrofit().getImage(tok, body);
                image.enqueue(new Callback<TuppianBean>() {
                    @Override
                    public void onResponse(Call<TuppianBean> call, Response<TuppianBean> response) {
                        MyToast.show("上传成功");
                        TuppianBean body1 = response.body();

                        url = body1.getResult().getUrl();
                        GlideUtils.loadHead(ghw, url);
                    }

                    @Override
                    public void onFailure(Call<TuppianBean> call, Throwable throwable) {
                        MyToast.show("上传失败");
                    }
                });

            }
        });
    }
}
