package com.yd.ye.main.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.bean.YeBean;
import com.xzq.module_base.bean.YuerBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.YeAdapter;
import com.yd.ye.main.biz.VoideActivity;
import com.yd.ye.main.biz.YexiangqingActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Tab2Fragment
 * Created by xzq on 2020/8/4.
 */
public class Tab2Fragment extends BasePresenterFragment {


    @BindView(R.id.yelist)
    RecyclerView yelist;
    private ArrayList<YeBean.ListBean> listBeans;

    private YeAdapter yeAdapter;
   private ArrayList<YeBean.ListBean> listwz;
    private ArrayList<YeBean.ListBean> listsp;
    private String types;
    private String typess;

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_tab2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        types = getArguments().getString("type");
        typess = (String) getArguments().get("types");
        listwz = new ArrayList<>();
        listsp = new ArrayList<>();
        if(typess.equals("225")){
            Yeretrofitsp(typess);//视频
        }
        else if(typess.equals("224")){
            Yeretrofitsp(typess);//视频
        }
        else if(typess.equals("223")){
            Yeretrofitsp(typess);//视频
        }
        else if(typess.equals("222")){
            Yeretrofitsp(typess);//视频
        }
        else if(typess.equals("221")){
            Yeretrofitsp(typess);//视频
        }


    }

    public void Yeretrofit(String type) {
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String result = token.getString("result", null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        yelist.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yelist.setLayoutManager(layoutManager);
        listBeans = new ArrayList<>();
        yeAdapter = new YeAdapter(listBeans, getContext());
        yelist.setAdapter(yeAdapter);
        YuerBean yuerBean = new YuerBean();
        YuerBean.ParamBean paramBean = new YuerBean.ParamBean();
        paramBean.setType(type);
        yuerBean.setParam(paramBean);
        Call<YeBean> ye = RetrofitUtils.getretrofit().getYe(result, BaseJsonParam.create(JsonParamUtils.Yejson(yuerBean)));
        ye.enqueue(new Callback<YeBean>() {
            @Override
            public void onResponse(Call<YeBean> call, Response<YeBean> response) {
                YeBean body = response.body();
                List<YeBean.ListBean> list = body.getList();
                if (list != null) {
                    if (list.size() != 0) {
                        listBeans.clear();
                        listBeans.addAll(list);
                        for (int i = 0; i < listBeans.size(); i++) {
                            i+=2;
                            if(listsp.size()!=0) {
                                listBeans.add(i, listsp.get(0));
                                listsp.remove(0);
                                // i+=1;
                            }
                        }
                        listBeans.addAll(listsp);
                        yeAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<YeBean> call, Throwable t) {

            }
        });
        yeAdapter.setJiekouhuidiaos(new YeAdapter.Jiekouhuidiao() {
            @Override
            public void OnClike(int position) {
                String content = listBeans.get(position).getContent();
                String id = listBeans.get(position).getId();
                if(content.contains(".mp4")==true){
                    //跳视频
                    Intent intent = new Intent(getContext(), VoideActivity.class);
                    intent.putExtra("url",content);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else {
                    //跳文章

                    int star = listBeans.get(position).getStar();
                    String s = String.valueOf(star);
                    Intent intent = new Intent(getContext(), YexiangqingActivity.class);
                    intent.putExtra("url", content);
                    intent.putExtra("id", id);
                    intent.putExtra("s", s);
                    startActivity(intent);
                }

            }
        });
    }

    public void Yeretrofitsp(String type) {
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String result = token.getString("result", null);
        YuerBean yuerBean = new YuerBean();
        YuerBean.ParamBean paramBean = new YuerBean.ParamBean();
        paramBean.setType(type);
        yuerBean.setParam(paramBean);
        Call<YeBean> ye = RetrofitUtils.getretrofit().getYe(result, BaseJsonParam.create(JsonParamUtils.Yejson(yuerBean)));
        ye.enqueue(new Callback<YeBean>() {
            @Override
            public void onResponse(Call<YeBean> call, Response<YeBean> response) {
                YeBean body = response.body();
                List<YeBean.ListBean> list = body.getList();
                if (list != null) {
                    if (list.size() != 0) {
                        listsp.clear();
                        listsp.addAll(list);
                        if (types.equals("215")) {
                            Yeretrofit(types);

                        } else if (types.equals("216")) {
                            Yeretrofit(types);

                        } else if (types.equals("217")) {
                            Yeretrofit(types);

                        } else if (types.equals("218")) {
                            Yeretrofit(types);

                        } else if (types.equals("219")) {
                            Yeretrofit(types);

                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<YeBean> call, Throwable t) {

            }
        });
    }
}
