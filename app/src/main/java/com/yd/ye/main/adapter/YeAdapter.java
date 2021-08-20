package com.yd.ye.main.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xzq.module_base.bean.YeBean;
import com.yd.ye.R;
import com.yd.ye.main.biz.CrlimageUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<YeBean.ListBean> listBeans;
    private Context context;

    public YeAdapter(ArrayList<YeBean.ListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate;
        if (viewType == 2) {
             inflate = LayoutInflater.from(context).inflate(R.layout.item_yewenzhang, parent, false);
            MyHolder myHolder = new MyHolder(inflate);
            return myHolder;
        } else if (viewType == 1) {
             inflate = LayoutInflater.from(context).inflate(R.layout.item_yeshipin, parent, false);
            MyHolder2 myHolder2 = new MyHolder2(inflate);
            return myHolder2;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CrlimageUtils crlimageUtils = new CrlimageUtils();
        RequestOptions yuan = crlimageUtils.yuan(20);

        int itemViewType = getItemViewType(position);
        if(itemViewType==2){
            MyHolder myHolder=(MyHolder) holder;
        Glide.with(context).load(listBeans.get(position).getCoverImgUrl()).apply(yuan).into(myHolder.im);
        myHolder.nr.setText(listBeans.get(position).getName());
        } if(itemViewType==1){

            MyHolder2 myHolder2= (MyHolder2) holder;
            Glide.with(context).load(listBeans.get(position).getCoverImgUrl()).apply(yuan).into(myHolder2.ims);
            myHolder2.tv.setText(listBeans.get(position).getName());
        }

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

    @Override
    public int getItemViewType(int position) {
        String content = listBeans.get(position).getContent();
        if (content.contains(".mp4")){
            return 1;
        }else {
            return 2;
        }
//        position++;
//        if(position%3==0){
//            return 1;//视频
//        }else {
//            return 2;//文章
//        }

    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView im;
        TextView nr;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
             im = itemView.findViewById(R.id.im);
             nr = itemView.findViewById(R.id.nr);


        }
    }

    class MyHolder2 extends RecyclerView.ViewHolder {
        ImageView ims;
        TextView tv;
        ImageView bf;
        public MyHolder2(@NonNull View itemView) {
            super(itemView);
            ims = itemView.findViewById(R.id.ims);
            tv = itemView.findViewById(R.id.tv);
            bf = itemView.findViewById(R.id.bf);


        }
    }
    public interface  Jiekouhuidiao{
        void OnClike(int position);
    }
    public Jiekouhuidiao jiekouhuidiao;
    public void setJiekouhuidiaos(YeAdapter.Jiekouhuidiao jiekouhuidiao) {
        this.jiekouhuidiao = jiekouhuidiao;
    }
}
