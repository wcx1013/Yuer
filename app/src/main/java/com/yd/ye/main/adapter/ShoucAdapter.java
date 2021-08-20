package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xzq.module_base.bean.ScxiangyinBean;
import com.yd.ye.R;
import com.yd.ye.main.biz.CrlimageUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoucAdapter extends RecyclerView.Adapter {
    private ArrayList<ScxiangyinBean.ListBean> listBeans;

    private Context context;

    public ShoucAdapter(ArrayList<ScxiangyinBean.ListBean> listBeans,  Context context) {
        this.listBeans = listBeans;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_yewenzhang, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
        CrlimageUtils crlimageUtils = new CrlimageUtils();
        RequestOptions yuan = crlimageUtils.yuan(20);
        Glide.with(context).load(listBeans.get(position).getCoverImgUrl()).apply(yuan).into(myHolder.imd);
        myHolder.name1.setText(listBeans.get(position).getName());
        //  myHolder.name2.setText(listBeans.get(position).getContent());


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
        TextView name1;
        //   TextView name2;
        ImageView imd;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.nr);
            //     name2 = itemView.findViewById(R.id.tv2);
            imd = itemView.findViewById(R.id.im);


        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
    }
    public Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(ShoucAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
