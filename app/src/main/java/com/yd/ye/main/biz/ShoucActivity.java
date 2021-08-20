package com.yd.ye.main.biz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.ScxiangyinBean;
import com.xzq.module_base.bean.ShouqinqiuBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;
import com.yd.ye.main.adapter.ShoucAdapter;
import com.yd.ye.main.fragments.OwnabonusFragment;
import com.yd.ye.main.fragments.TeamabonusFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoucActivity extends BasePresenterActivity {

    @BindView(R.id.redios)
    RadioGroup redios;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.redio1)
    RadioButton redio1;
    @BindView(R.id.redio2)
    RadioButton redio2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_listsc;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            hideToolbar();
        redios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.redio1:
                        //点击第一个radiobutton,显示viewpager的第一页
                        viewpager.setCurrentItem(0, false);
                        break;
                    case R.id.redio2:
                        //点击第二个radiobutton,显示viewpager的第二页
                        viewpager.setCurrentItem(1, false);
                        break;
                }
            }
        });

        viewpager.setAdapter(new FragmentPagerAdapter(me.getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 2;
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        //当滑动到第一页时候,展示这个fragment
                        fragment = new OwnabonusFragment();
                        fragment.setArguments(bundle);
                        break;
                    case 1:
                        //当滑动到第二页时候,展示这个fragment
                        fragment = new TeamabonusFragment();
                        fragment.setArguments(bundle);
                        break;

                }
                return fragment;
            }
        });


        //viewpager滑动监听

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //radiogroup选中对应的radiobutton
                redios.check(redios.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.redio1, R.id.redio2, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackClick();
                break;
            case R.id.redio1:
                redio1.setChecked(true);
                break;
            case R.id.redio2:
                redio2.setChecked(true);
                break;
        }
    }





}
