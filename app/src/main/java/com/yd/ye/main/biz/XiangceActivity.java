package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.HomeBean;
import com.xzq.module_base.bean.HomereposeBean;
import com.xzq.module_base.bean.ShanBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;

import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.XcAdapter;


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

public class XiangceActivity extends BasePresenterActivity {
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.xclist)
    RecyclerView xclist;
    @BindView(R.id.adds)
    ImageView adds;
    @BindView(R.id.bjs)
    ImageView bjs;
    @BindView(R.id.shanc)
    ImageView shanc;
    @BindView(R.id.qxs)
    TextView qxs;
    private XcAdapter xcAdapter;
    private ArrayList<String> idlist;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xc;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        idlist = new ArrayList<>();
        Xiacerefit(false);

    }

    private void Xiacerefit(boolean isshow) {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        GridLayoutManager layoutManager = new GridLayoutManager(me, 2);
        xclist.setLayoutManager(layoutManager);
        xclist.setNestedScrollingEnabled(false);
        ArrayList<HomereposeBean.ListBean> listBeanss = new ArrayList<>();

        xcAdapter = new XcAdapter(listBeanss, this, isshow);
        xclist.setAdapter(xcAdapter);

        HomeBean homeBean = new HomeBean();
        homeBean.setPageNum(1);
        homeBean.setPageSize(20);
        HomeBean.ParamBean paramBean = new HomeBean.ParamBean();
        paramBean.setType(4);
        homeBean.setParam(paramBean);
        Call<HomereposeBean> gethomelist = RetrofitUtils.getretrofit().gethomelist(result, BaseJsonParam.create(JsonParamUtils.gethomelist(homeBean)));
        gethomelist.enqueue(new Callback<HomereposeBean>() {
            @Override
            public void onResponse(Call<HomereposeBean> call, Response<HomereposeBean> response) {
                List<HomereposeBean.ListBean> list = response.body().getList();
                if (list != null) {
                    if (list.size() != 0) {
                        listBeanss.clear();
                        listBeanss.addAll(list);
                        xcAdapter.notifyDataSetChanged();
//                        jnrrecyc.setVisibility(View.VISIBLE);
//                        ts.setVisibility(View.GONE);
                    }
                    //                   else {
//                        jnrrecyc.setVisibility(View.GONE);
//                        ts.setVisibility(View.VISIBLE);
//                    }
                }


            }

            @Override
            public void onFailure(Call<HomereposeBean> call, Throwable throwable) {

            }
        });

        xcAdapter.setJiekouhuidiaos(new XcAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                String id = listBeanss.get(position).getId();
                String remarks = listBeanss.get(position).getRemarks();
                Intent intent = new Intent(XiangceActivity.this, XiangpActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("remarks",remarks);
                startActivity(intent);
            }

            @Override
            public void OnClikeselect(int position, View v) {
                //选择
                if (listBeanss != null) {
                    View viewByPosition = layoutManager.findViewByPosition(position);
                    LinearLayout layout = (LinearLayout) viewByPosition;
                    ImageView gou = layout.findViewById(R.id.gou);
                    ImageView meigou = layout.findViewById(R.id.meigou);
                    gou.setVisibility(View.VISIBLE);
                    meigou.setVisibility(View.GONE);

                    String ids = listBeanss.get(position).getId();
                    idlist.add(ids);
                }
            }

            @Override
            public void Onwc(int position) {
                if (listBeanss != null) {
                    View viewByPosition = layoutManager.findViewByPosition(position);
                    LinearLayout layout = (LinearLayout) viewByPosition;
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
        Xiacerefit(false);
    }

    @OnClick({R.id.fan, R.id.adds, R.id.bjs, R.id.shanc,R.id.qxs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fan:
                finish();
                break;
            case R.id.adds:
                Intent intent = new Intent(XiangceActivity.this, AddxcActivity.class);
                startActivity(intent);
                break;
            case R.id.bjs:
                boolean isshow = true;
                Xiacerefit(isshow);
                bjs.setVisibility(View.GONE);
                qxs.setVisibility(View.VISIBLE);
                shanc.setVisibility(View.VISIBLE);
                break;
            case R.id.shanc:
                if(idlist!=null){
                    if (idlist.size()!=0){
                        shanRetorfit();
                    }
                }
                break;
            case R.id.qxs:
                boolean isshows = false;
                Xiacerefit(isshows);
                qxs.setVisibility(View.GONE);
                bjs.setVisibility(View.VISIBLE);
                shanc.setVisibility(View.GONE);
                if(idlist!=null){
                    if(idlist.size()!=0){
                        idlist.clear();
                    }
                }
                break;
        }
    }

    private void shanRetorfit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        ShanBean shanBean = new ShanBean();
        ShanBean.ParamBean paramBean = new ShanBean.ParamBean();
        paramBean.setResIdList(idlist);
        shanBean.setParam(paramBean);

        Call<ShanBean> getdelect = RetrofitUtils.getretrofit().getdelect(result, BaseJsonParam.create(JsonParamUtils.getshan(shanBean)));
        getdelect.enqueue(new Callback<ShanBean>() {
            @Override
            public void onResponse(Call<ShanBean> call, Response<ShanBean> response) {

                Xiacerefit(false);
            }

            @Override
            public void onFailure(Call<ShanBean> call, Throwable t) {

            }
        });
    }


}
