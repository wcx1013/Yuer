package com.yd.ye.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.bean.CategoryDto;
import com.yd.ye.R;
import com.yd.ye.main.adapter.MyOrderAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Tab1Fragment
 * Created by xzq on 2020/8/4.
 */
public class Tab1Fragment extends BasePresenterFragment {

    @BindView(R.id.ban)
    Banner mBanner;
    @BindView(R.id.tablayout)
    SlidingTabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<String> mTitle = new ArrayList<>();
    private int type=215;
    private int types=225;
    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_tab1;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //banner
        ArrayList<Integer> bannerlist = new ArrayList<>();
        bannerlist.add(R.drawable.bannerone);
        bannerlist.add(R.drawable.qidong);
        mBanner.setImages(bannerlist).setImageLoader(new Glige()).start();
        //tablayout
        List<CategoryDto> bean=new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        bean.add(0, new CategoryDto("-5", "备孕期"));
        bean.add(1, new CategoryDto("-4","怀孕期"));
        bean.add(2, new CategoryDto("-3","新生期"));
        bean.add(3, new CategoryDto("-2","婴儿期"));
        bean.add(4, new CategoryDto("-1","幼儿期"));
        for (int i = 0; i < bean.size(); i++) {
            Tab2Fragment tab2Fragment = new Tab2Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("type",  String.valueOf(type++));
            bundle.putString("types",  String.valueOf(types--));

            tab2Fragment.setArguments(bundle);
            fragmentList.add(tab2Fragment);
        }
        mTitle.add(bean.get(0).name);
        mTitle.add(bean.get(1).name);
        mTitle.add(bean.get(2).name);
        mTitle.add(bean.get(3).name);
        mTitle.add(bean.get(4).name);


        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(getChildFragmentManager(), fragmentList, mTitle);
        viewpager.setAdapter(myOrderAdapter);
        tablayout.setViewPager(viewpager);


    }
    class Glige extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
