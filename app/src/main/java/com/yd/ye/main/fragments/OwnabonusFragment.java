package com.yd.ye.main.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.bean.ScxiangyinBean;
import com.xzq.module_base.bean.ShouqinqiuBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.ShoucAdapter;
import com.yd.ye.main.biz.ShoucActivity;
import com.yd.ye.main.biz.YexiangqingActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnabonusFragment extends BasePresenterFragment {

    @BindView(R.id.screcyc)
    RecyclerView screcyc;
    @BindView(R.id.tsas)
    TextView tsas;

        private int pageNum = 1;
    private ArrayList<ScxiangyinBean.ListBean> listBeans;
    private ShoucAdapter shoucAdapter;
    private List<ScxiangyinBean.ListBean> list;
    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_wz;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        screfit();
    }


        public void screfit() {
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String result = token.getString("result", null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        screcyc.setLayoutManager(layoutManager);
        listBeans = new ArrayList<>();
        shoucAdapter = new ShoucAdapter(listBeans, getContext());
        screcyc.setAdapter(shoucAdapter);

        ShouqinqiuBean shouqinqiuBean = new ShouqinqiuBean();
        shouqinqiuBean.setPageNum(pageNum);
        shouqinqiuBean.setPageSize(10);
        ShouqinqiuBean.ParamBean paramBean = new ShouqinqiuBean.ParamBean();
        int ww = 2;
        paramBean.setResType(String.valueOf(ww));
        shouqinqiuBean.setParam(paramBean);
        Call<ScxiangyinBean> getsclist = RetrofitUtils.getretrofit().getsclist(result, BaseJsonParam.create(JsonParamUtils.getshoulist(shouqinqiuBean)));
        getsclist.enqueue(new Callback<ScxiangyinBean>() {
            @Override
            public void onResponse(Call<ScxiangyinBean> call, Response<ScxiangyinBean> response) {
                list = response.body().getList();
                if (list != null) {
                    if(list.size()!=0){
                        listBeans.clear();
                        listBeans.addAll(list);
                        shoucAdapter.notifyDataSetChanged();
                        screcyc.setVisibility(View.VISIBLE);
                        tsas.setVisibility(View.GONE);
                    }else {
                        screcyc.setVisibility(View.GONE);
                        tsas.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ScxiangyinBean> call, Throwable t) {

            }
        });


        shoucAdapter.setJiekouhuidiaos(new ShoucAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                String content = list.get(position).getContent();
                String id = list.get(position).getId();
                int star = list.get(position).getStar();
                String s = String.valueOf(star);
                Intent intent = new Intent(getContext(), YexiangqingActivity.class);
                intent.putExtra("url", content);
                intent.putExtra("id", id);
                intent.putExtra("s", s);
                startActivity(intent);

            }
        });
    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
                String msg = intent.getStringExtra("data");
                if("refresh".equals(msg)){
                    screfit();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

}
