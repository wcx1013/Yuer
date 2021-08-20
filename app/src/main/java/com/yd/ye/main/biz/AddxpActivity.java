package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giftedcat.picture.lib.selector.MultiImageSelector;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.AddzpBean;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.bean.TuppianBean;
import com.xzq.module_base.managers.LubanManager;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.NineGridAdapter;
import com.yd.ye.main.adapter.OnAddPicturesListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddxpActivity extends BasePresenterActivity {


    NineGridAdapter adapter;

    List<String> mSelectList;
    private static final int REQUEST_IMAGE = 2;
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.zplist)
    RecyclerView zplist;
    @BindView(R.id.hui)
    TextView hui;
    @BindView(R.id.fen)
    TextView fen;
    private int maxNum = 20;
    private StringBuilder sb;
    private String url;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zp;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        id = getIntent().getStringExtra("id");
        mSelectList = new ArrayList<>();
        initdata();
        fen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initdata() {
        zplist.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new NineGridAdapter(this, mSelectList, zplist);
        adapter.setMaxSize(maxNum);
        zplist.setAdapter(adapter);
        adapter.setOnAddPicturesListener(new OnAddPicturesListener() {
            @Override
            public void onAdd() {
                pickImage();
            }
        });
    }


    /**
     * 选择需添加的图片
     */
    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create(this);
        selector.showCamera(true);
        selector.count(maxNum);
        selector.multi();
        selector.origin(mSelectList);
        selector.start(this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                mSelectList.clear();
                mSelectList.addAll(select);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < mSelectList.size(); i++) {
                    Image(mSelectList.get(i));
                }
                hui.setVisibility(View.GONE);
                fen.setVisibility(View.VISIBLE);


            }

        }
    }


    public void Image(String localImgPath) {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String tok = token.getString("result", null);
        LubanManager.compressSingle(localImgPath, result -> {
            for (String p : result) {
                File file = new File(String.valueOf(p));
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                Call<TuppianBean> image = RetrofitUtils.getretrofit().getImage(tok, body);
                image.enqueue(new Callback<TuppianBean>() {
                    @Override
                    public void onResponse(Call<TuppianBean> call, Response<TuppianBean> response) {
                        //  MyToast.show("上传成功");
                        TuppianBean body1 = response.body();
                        url = body1.getResult().getUrl();
//                        sb.append(url + ",");
//                        Log.d("qwe",sb.toString());
                        addzprefit();//添加照片
                    }

                    @Override
                    public void onFailure(Call<TuppianBean> call, Throwable throwable) {
                        // MyToast.show("上传失败");
                    }
                });
//
            }
        });
    }

    private void addzprefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String tok = token.getString("result", null);
        AddzpBean addzpBean = new AddzpBean();
        AddzpBean.ParamBean paramBean = new AddzpBean.ParamBean();
        paramBean.setAlbumId(id);
        paramBean.setImgUrl(url);
        addzpBean.setParam(paramBean);
        Call<TokenBean> getaddzp = RetrofitUtils.getretrofit().getaddzp(tok, BaseJsonParam.create(JsonParamUtils.getAddzp(addzpBean)));
        getaddzp.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {

            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {

            }
        });
    }


    @OnClick(R.id.fan)
    public void onClick() {
        finish();
    }



}
