package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xzq.module_base.bean.ScxiangyinBean;
import com.yd.ye.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter {
    private ArrayList<ScxiangyinBean.ListBean> listBeans;

    private Context context;

    public VideoAdapter(ArrayList<ScxiangyinBean.ListBean> listBeans,  Context context) {
        this.listBeans = listBeans;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_yeshipin, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       MyHolder myHolder=(MyHolder) holder;
        Glide.with(context).load(listBeans.get(position).getCoverImgUrl()).into(myHolder.ims);
        myHolder.tv.setText(listBeans.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekouhuidiao.OnClike(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView ims;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ims = itemView.findViewById(R.id.ims);
            tv = itemView.findViewById(R.id.tv);


        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
    }
    public Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(VideoAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
