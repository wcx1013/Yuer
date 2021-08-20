package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.TongyongBean;
import com.xzq.module_base.bean.TuppianBean;
import com.xzq.module_base.bean.WuBean;
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
import java.util.List;

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

public class AddxcActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bars)
    Toolbar bars;
    @BindView(R.id.nanming)
    EditText nanming;
    @BindView(R.id.nvming)
    EditText nvming;
    @BindView(R.id.beijingtu)
    ImageView beijingtu;
    @BindView(R.id.qued)
    ImageView qued;
    private String path;
    private String url;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_addxc;
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


    @OnClick({R.id.beijingtu, R.id.qued})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beijingtu:
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
            case R.id.qued:
                Addxcrefit();
                break;
        }
    }

    private void Addxcrefit() {

        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String tok = token.getString("result", null);
        TongyongBean tongyongBean = new TongyongBean();

        TongyongBean.ParamBean paramBean = new TongyongBean.ParamBean();
        paramBean.setName(nanming.getText().toString());
        paramBean.setRemarks(nvming.getText().toString());
        paramBean.setCoverImg(url);
        paramBean.setType(4);
        tongyongBean.setParam(paramBean);

        Call<WuBean> gethomegai = RetrofitUtils.getretrofit().gethomegai(tok, BaseJsonParam.create(JsonParamUtils.gethomeuser(tongyongBean)));
        gethomegai.enqueue(new Callback<WuBean>() {
            @Override
            public void onResponse(Call<WuBean> call, Response<WuBean> response) {
                // WuBean body = response.body();
                //MyToast.show("添加成功");
                finish();
            }

            @Override
            public void onFailure(Call<WuBean> call, Throwable throwable) {

            }
        });
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
                        GlideUtils.loadHead(beijingtu, url);
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
