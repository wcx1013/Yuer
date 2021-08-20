package com.yd.ye.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.bean.HomereposeBean;
import com.yd.ye.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JnrAdapter extends RecyclerView.Adapter {
    private List<HomereposeBean.ListBean> list;

    private Context context;

    public JnrAdapter(ArrayList<HomereposeBean.ListBean> list, Context context) {
        this.list = list;

        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_js, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
        myHolder.name1.setText(list.get(position).getName());
        String planTime = list.get(position).getPlanTime();
        String substring = planTime.substring(0,10);
        myHolder.name2.setText(substring);

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
        TextView name1;
        TextView name2;
        ImageView im;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.tv1);
            name2 = itemView.findViewById(R.id.tv2);
            im = itemView.findViewById(R.id.im);


        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
    }
    public Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(JnrAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
