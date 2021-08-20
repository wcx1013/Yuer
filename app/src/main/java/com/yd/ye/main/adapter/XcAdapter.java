package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xzq.module_base.bean.HomereposeBean;
import com.xzq.module_base.utils.GlideUtils;
import com.yd.ye.R;
import com.yd.ye.main.biz.CrlimageUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class XcAdapter extends RecyclerView.Adapter {
    private List<HomereposeBean.ListBean> list;

    private Context context;
    private boolean isshow;
    public XcAdapter(ArrayList<HomereposeBean.ListBean> list, Context context,boolean isshow) {
        this.list = list;

        this.context = context;
        this.isshow=isshow;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_xc, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
        CrlimageUtils crlimageUtils = new CrlimageUtils();
        RequestOptions yuan = crlimageUtils.yuan(20);
        Glide.with(context).load(list.get(position).getCoverImg()).apply(yuan).into(myHolder.im);
        myHolder.name1.setText(list.get(position).getName());
        String planTime = list.get(position).getPlanTime();
        String substring = planTime.substring(0,10);
        myHolder.name3.setText(substring);
        if(isshow==false){

        }else {
            myHolder.mg.setVisibility(View.VISIBLE);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        TextView name1;
        TextView name2;
        TextView name3;
        ImageView im;
        ImageView gou;
        ImageView mg;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.name);
            name2 = itemView.findViewById(R.id.zs);
            name3 = itemView.findViewById(R.id.rq);
            im = itemView.findViewById(R.id.fm);
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
    public void setJiekouhuidiaos(XcAdapter.Jiekouhuidiao jiekouhuidiao) {
       this.jiekouhuidiao = jiekouhuidiao;
    }






}
