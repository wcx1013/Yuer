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
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.TzAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TizhongActivity extends BasePresenterActivity {
    @BindView(R.id.fans)
    ImageView fans;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.jias)
    ImageView jias;
    @BindView(R.id.tss)
    TextView tss;
    @BindView(R.id.tzlist)
    RecyclerView tzlist;
    @BindView(R.id.re)
    LinearLayout re;
    private TzAdapter tzAdapter;
    private PopupWindow popupWindow;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tz;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        Tzrefit();
    }

    private void Tzrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tzlist.setLayoutManager(layoutManager);
        ArrayList<HomereposeBean.ListBean> listBeanss = new ArrayList<>();
        tzAdapter = new TzAdapter(listBeanss, this);
        tzlist.setAdapter(tzAdapter);

        HomeBean homeBean = new HomeBean();
        homeBean.setPageNum(1);
        homeBean.setPageSize(30);
        HomeBean.ParamBean paramBean = new HomeBean.ParamBean();
        paramBean.setType(7);
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
                        tzAdapter.notifyDataSetChanged();
                        tzlist.setVisibility(View.VISIBLE);
                        tss.setVisibility(View.GONE);
                    } else {
                        tzlist.setVisibility(View.GONE);
                        tss.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onFailure(Call<HomereposeBean> call, Throwable throwable) {

            }
        });
        tzAdapter.setJiekouhuidiaos(new TzAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
//                if(position==0){
//                    String id = listBeanss.get(position).getId();
//                    Intent intent = new Intent(TizhongActivity.this, JlgaiActivity.class);
//                    intent.putExtra("id",id);
//                    startActivity(intent);
//                }
                //删除
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
        popupWindow.showAtLocation(re, Gravity.CENTER, 0, 0);
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
        Tzrefit();
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
                MyToast.show("删除成功");

            }

            @Override
            public void onFailure(Call<ShanBean> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.fans, R.id.jias})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fans:
                finish();
                break;
            case R.id.jias:
                Intent intent = new Intent(this, JluActivity.class);
                startActivity(intent);
                break;
        }
    }


}
