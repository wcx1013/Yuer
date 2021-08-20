package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.HomeBean;
import com.xzq.module_base.bean.HomereposeBean;
import com.xzq.module_base.bean.ShanBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.JnrAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JInianriActivity extends BasePresenterActivity {
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.jia)
    ImageView jia;
    @BindView(R.id.jnrrecyc)
    RecyclerView jnrrecyc;
    //    @BindView(R.id.smarts)
//    SmartRefreshLayout smarts;
    @BindView(R.id.reax)
    LinearLayout reax;
    @BindView(R.id.ts)
    TextView ts;
    private JnrAdapter jnrAdapter;
    private int pageNum;
    private PopupWindow popupWindow;
    private String id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_jnr;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        HomedaoRetrofit();
//        smarts.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                smarts.finishLoadMore();
//                pageNum++;
//                HomedaoRetrofit();
//
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                smarts.finishRefresh();
//                pageNum = 1;
//                HomedaoRetrofit();
//
//            }
//        });
    }


    @OnClick({R.id.fan, R.id.jia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fan:
                finish();
                break;
            case R.id.jia:
                Intent intent = new Intent(JInianriActivity.this, AddjnrActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void HomedaoRetrofit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jnrrecyc.setLayoutManager(layoutManager);
        ArrayList<HomereposeBean.ListBean> listBeanss = new ArrayList<>();

        jnrAdapter = new JnrAdapter(listBeanss, this);
        jnrrecyc.setAdapter(jnrAdapter);

        HomeBean homeBean = new HomeBean();
        homeBean.setPageNum(pageNum);
        homeBean.setPageSize(5);
        HomeBean.ParamBean paramBean = new HomeBean.ParamBean();
        paramBean.setType(6);
        homeBean.setParam(paramBean);
        Call<HomereposeBean> gethomelist = RetrofitUtils.getretrofit().gethomelist(result, BaseJsonParam.create(JsonParamUtils.gethomelist(homeBean)));
        gethomelist.enqueue(new Callback<HomereposeBean>() {
            @Override
            public void onResponse(Call<HomereposeBean> call, Response<HomereposeBean> response) {
                List<HomereposeBean.ListBean> list = response.body().getList();
                if(list!=null){
                    if (list.size()!=0) {
                        listBeanss.clear();
                        listBeanss.addAll(list);
                        jnrAdapter.notifyDataSetChanged();
                        jnrrecyc.setVisibility(View.VISIBLE);
                        ts.setVisibility(View.GONE);
                    }else {
                        jnrrecyc.setVisibility(View.GONE);
                        ts.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onFailure(Call<HomereposeBean> call, Throwable throwable) {

            }
        });
        jnrAdapter.setJiekouhuidiaos(new JnrAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                id = listBeanss.get(position).getId();
                popupwindon();
            }
        });

    }

    private void popupwindon() {

        View view = LayoutInflater.from(me).inflate(R.layout.layout_shan, null);
        TextView no = view.findViewById(R.id.zaixiangxiang);
        TextView ok = view.findViewById(R.id.tongyi);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(reax, Gravity.CENTER, 0, 0);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShanRefit();
                popupWindow.dismiss();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        HomedaoRetrofit();
    }

    private void ShanRefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        ShanBean shanBean = new ShanBean();
        ShanBean.ParamBean paramBean = new ShanBean.ParamBean();
        paramBean.setResIdList(Collections.singletonList(id));
        shanBean.setParam(paramBean);

        Call<ShanBean> getdelect = RetrofitUtils.getretrofit().getdelect(result, BaseJsonParam.create(JsonParamUtils.getshan(shanBean)));
        getdelect.enqueue(new Callback<ShanBean>() {
            @Override
            public void onResponse(Call<ShanBean> call, Response<ShanBean> response) {
                // MyToast.show("删除成功");
                HomedaoRetrofit();
            }

            @Override
            public void onFailure(Call<ShanBean> call, Throwable t) {

            }
        });
    }


}
