package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.xzq.module_base.bean.TingBean;
import com.xzq.module_base.utils.GlideUtils;
import com.yd.ye.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TingAdapter extends RecyclerView.Adapter {
    private ArrayList<TingBean.ListBean> list;
    private Context context;

    public TingAdapter(ArrayList<TingBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_ting, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;

        RoundedCorners roundedCorners= new RoundedCorners(20);
//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners);


        Glide.with(context).load(list.get(position).getCoverImgUrl()).apply(options).into(myHolder.image);
        myHolder.spbt.setText(list.get(position).getName());


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

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imas);
            spbt = itemView.findViewById(R.id.tva);

        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
    }
    Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(TingAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
