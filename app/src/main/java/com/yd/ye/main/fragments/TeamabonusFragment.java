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
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.bean.ScxiangyinBean;
import com.xzq.module_base.bean.ShouqinqiuBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.ShoucAdapter;
import com.yd.ye.main.adapter.SpAdapter;
import com.yd.ye.main.biz.VoideActivity;
import com.yd.ye.main.biz.YexiangqingActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamabonusFragment extends BasePresenterFragment {
    @BindView(R.id.listsp)
    RecyclerView listsp;
    @BindView(R.id.tsas)
    TextView tsas;
    private int pageNum = 1;
    private ArrayList<ScxiangyinBean.ListBean> listBeans;

    private List<ScxiangyinBean.ListBean> list;
    private SpAdapter spAdapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_sp;
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
        listsp.setLayoutManager(layoutManager);
        listBeans = new ArrayList<>();
        spAdapter = new SpAdapter(listBeans, getContext());
        listsp.setAdapter(spAdapter);

        ShouqinqiuBean shouqinqiuBean = new ShouqinqiuBean();
        shouqinqiuBean.setPageNum(pageNum);
        shouqinqiuBean.setPageSize(10);
        ShouqinqiuBean.ParamBean paramBean = new ShouqinqiuBean.ParamBean();
        int ww = 1;
        paramBean.setResType(String.valueOf(ww));
        shouqinqiuBean.setParam(paramBean);
        Call<ScxiangyinBean> getsclist = RetrofitUtils.getretrofit().getsclist(result, BaseJsonParam.create(JsonParamUtils.getshoulist(shouqinqiuBean)));
        getsclist.enqueue(new Callback<ScxiangyinBean>() {
            @Override
            public void onResponse(Call<ScxiangyinBean> call, Response<ScxiangyinBean> response) {
                list = response.body().getList();
                if (list != null) {
                    if (list.size() != 0) {
                        listBeans.clear();
                        listBeans.addAll(list);
                        spAdapter.notifyDataSetChanged();
                        listsp.setVisibility(View.VISIBLE);
                        tsas.setVisibility(View.GONE);
                    } else {
                        listsp.setVisibility(View.GONE);
                        tsas.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ScxiangyinBean> call, Throwable t) {

            }
        });



        spAdapter.setJiekouhuidiaos(new SpAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                String content = listBeans.get(position).getContent();
                String id = listBeans.get(position).getId();
                Intent intent = new Intent(getContext(), VoideActivity.class);
                intent.putExtra("url",content);
                intent.putExtra("id", id);
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
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("data");
                if ("refresh".equals(msg)) {
                    screfit();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

}
