package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.bean.CuiBean;
import com.xzq.module_base.utils.GlideUtils;
import com.yd.ye.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CuiAdapter extends RecyclerView.Adapter {
    private ArrayList<CuiBean.ListBean> list;
    private Context context;
    private boolean isshow;

    public CuiAdapter(ArrayList<CuiBean.ListBean>  list, Context context,boolean isshow) {
        this.list = list;
        this.context = context;
        this.isshow=isshow;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cui, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
        GlideUtils.loadHead(myHolder.im, list.get(position).getImgUrl());
        if(isshow==false){

        }else {
            myHolder.mg.setVisibility(View.VISIBLE);
        }
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekouhuidiao.OnClike(position);
            }
        });

        myHolder.mg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekouhuidiao.OnClikeselect(position,v);
            }
        });
        myHolder.gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekouhuidiao.Onwc(position);
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }




    class MyHolder extends RecyclerView.ViewHolder {
        ImageView im;
        ImageView gou;
        ImageView mg;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
             im = itemView.findViewById(R.id.ima);
             gou = itemView.findViewById(R.id.gou);
             mg = itemView.findViewById(R.id.meigou);


        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
        void OnClikeselect(int position,View v);
        void Onwc(int position);
    }
    public Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(CuiAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
