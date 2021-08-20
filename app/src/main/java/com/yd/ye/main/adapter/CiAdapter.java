package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xzq.module_base.bean.CiyBean;
import com.yd.ye.R;
import com.yd.ye.main.biz.CrlimageUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CiAdapter extends RecyclerView.Adapter {
    private ArrayList<CiyBean.ListBean> list;
    private Context context;

    public CiAdapter(ArrayList<CiyBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_eg, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
        CrlimageUtils crlimageUtils = new CrlimageUtils();
        RequestOptions yuan = crlimageUtils.yuan(20);
        Glide.with(context).load(list.get(position).getCoverImgUrl()).apply(yuan).into(myHolder.image);
        myHolder.spbt.setText(list.get(position).getName());
       // myHolder.spb.setText(list.get(position).getTagsName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekouhuidiao.OnClike(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView spbt;
        TextView spb;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.im_a);
            spbt = itemView.findViewById(R.id.tv_a);
            spb = itemView.findViewById(R.id.tv_b);

        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
    }
   Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(CiAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
