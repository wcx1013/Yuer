package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.CuiBean;
import com.xzq.module_base.bean.HscBean;
import com.xzq.module_base.bean.QuBean;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.CuiAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XiangpActivity extends BasePresenterActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.zplist)
    RecyclerView rvImages;

    @BindView(R.id.addl)
    ImageView addl;
    @BindView(R.id.fans)
    ImageView fans;
    @BindView(R.id.bj)
    ImageView bj;
    @BindView(R.id.sc)
    ImageView sc;
    @BindView(R.id.qx)
    TextView qx;
    @BindView(R.id.qw)
    TextView qw;
    private int maxNum = 9;

    private String id;
    private CuiAdapter cuiAdapter;
    private ArrayList<CuiBean.ListBean> listBeans;
    private ArrayList<String> idlist;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_findxc;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        idlist = new ArrayList<>();
        id = getIntent().getStringExtra("id");
        String remarks = getIntent().getStringExtra("remarks");
        qw.setText(remarks);
        Findxprefit(false);//查询
        addl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiangpActivity.this, AddxpActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        fans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Findxprefit(boolean isshow) {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        GridLayoutManager layoutManager = new GridLayoutManager(me, 3);
        rvImages.setLayoutManager(layoutManager);
        rvImages.setNestedScrollingEnabled(false);
        listBeans = new ArrayList<>();
        cuiAdapter = new CuiAdapter(listBeans, this, isshow);
        rvImages.setAdapter(cuiAdapter);
        QuBean quBean = new QuBean();

        QuBean.ParamBean paramBean = new QuBean.ParamBean();
        paramBean.setAlbumId(id);
        quBean.setParam(paramBean);
        Call<CuiBean> getwx = RetrofitUtils.getretrofit().getwx(result, BaseJsonParam.create(JsonParamUtils.getwxc(quBean)));
        getwx.enqueue(new Callback<CuiBean>() {
            @Override
            public void onResponse(Call<CuiBean> call, Response<CuiBean> response) {
                List<CuiBean.ListBean> list = response.body().getList();
                if (list != null) {
                    if (list.size() != 0) {
                        listBeans.clear();
                        listBeans.addAll(list);
                        cuiAdapter.notifyDataSetChanged();
//                        cuiqianrecyc.setVisibility(View.VISIBLE);
//                        tso.setVisibility(View.GONE);
                    } else {
//                        cuiqianrecyc.setVisibility(View.GONE);
//                        tso.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void onFailure(Call<CuiBean> call, Throwable t) {

            }
        });
        cuiAdapter.setJiekouhuidiaos(new CuiAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                if (listBeans != null) {
                    MyToast.show("HDYWERWYGRWERGWEUIRHE");
                }

            }

            @Override
            public void OnClikeselect(int position, View view) {
                //选择
                if (listBeans != null) {
                    View viewByPosition = layoutManager.findViewByPosition(position);
                    FrameLayout layout = (FrameLayout) viewByPosition;
                    ImageView gou = layout.findViewById(R.id.gou);
                    ImageView meigou = layout.findViewById(R.id.meigou);
                    gou.setVisibility(View.VISIBLE);
                    meigou.setVisibility(View.GONE);

                    String ids = listBeans.get(position).getId();
                    idlist.add(ids);
                }

            }

            @Override
            public void Onwc(int position) {
                if (listBeans != null) {
                    View viewByPosition = layoutManager.findViewByPosition(position);
                    FrameLayout layout = (FrameLayout) viewByPosition;
                    ImageView gou = layout.findViewById(R.id.gou);
                    ImageView meigou = layout.findViewById(R.id.meigou);
                    gou.setVisibility(View.GONE);
                    meigou.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Findxprefit(false);//查询
    }

    @OnClick({R.id.bj, R.id.sc, R.id.qx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bj:
                boolean isshow = true;
                Findxprefit(isshow);
                bj.setVisibility(View.GONE);
                qx.setVisibility(View.VISIBLE);
                sc.setVisibility(View.VISIBLE);
                break;
            case R.id.sc:
                if (idlist != null) {
                    if (idlist.size() != 0) {
                        shanRetorfit();
                    }
                }


                break;
            case R.id.qx:
                boolean isshows = false;
                Findxprefit(isshows);
                qx.setVisibility(View.GONE);
                bj.setVisibility(View.VISIBLE);
                sc.setVisibility(View.GONE);
                if (idlist != null) {
                    if (idlist.size() != 0) {
                        idlist.clear();
                    }
                }

                break;
        }
    }

    private void shanRetorfit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        HscBean hscBean = new HscBean();
        HscBean.ParamBean paramBean = new HscBean.ParamBean();
        paramBean.setResIdList(idlist);
        hscBean.setParam(paramBean);
        Call<TokenBean> getshan = RetrofitUtils.getretrofit().getshan(result, BaseJsonParam.create(JsonParamUtils.gethsc(hscBean)));
        getshan.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {

                Findxprefit(false);//查询
            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {

            }
        });
    }

}
