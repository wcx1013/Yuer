package com.yd.ye.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.bean.HomereposeBean;
import com.xzq.module_base.utils.SlidingButtonView;
import com.yd.ye.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TzAdapter extends RecyclerView.Adapter  {
    private List<HomereposeBean.ListBean> list;

    private Context context;

    public TzAdapter(ArrayList<HomereposeBean.ListBean> list, Context context) {
        this.list = list;

        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_tz, parent, false);
       MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;

            myHolder.text.setText(list.get(position).getCreateTime());
            String remarks = list.get(position).getRemarks();
            myHolder.shengao.setText("身高"+remarks);
            String matching = list.get(position).getMatching();
            myHolder.tizhong.setText("体重"+matching);

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer insert = stringBuffer.append(remarks).insert(1, ".");
        Float remarks1 = Float.valueOf(insert + "");
        Float matching1 = Float.valueOf(matching);

        Float wcx=remarks1*remarks1;
        Float cx=matching1/wcx;
            String s = String.valueOf(cx);
            myHolder.bmi.setText("BMI:"+s);

//        }else {
//            myHolder.text.setText("孕前");
//            myHolder.text.setTextColor(R.color.tt_appdownloader_notification_title_color);
//            myHolder.shengao.setTextColor(R.color.tt_appdownloader_notification_title_color);
//            myHolder.tizhong.setTextColor(R.color.tt_appdownloader_notification_title_color);
//            myHolder.bmi.setTextColor(R.color.tt_appdownloader_notification_title_color);
//
//
//        }


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

        TextView text;
        TextView shengao;
        TextView tizhong;
        TextView bmi;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            shengao = itemView.findViewById(R.id.shengao);
            tizhong = itemView.findViewById(R.id.tizhong);
            bmi = itemView.findViewById(R.id.bmi);
        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);

    }
    public Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(TzAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
