package com.yd.ye.main.biz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.TongyongBean;
import com.xzq.module_base.bean.WuBean;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.RetrofitUtils;
import com.yd.ye.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddjnrActivity extends BasePresenterActivity {
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.wancheng)
    TextView wancheng;
    @BindView(R.id.nr)
    EditText nr;
    @BindView(R.id.time)
    TextView times;
    private CustomDatePicker customDatePicker;
    private String now;
    private String chushengriqi;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_addjnr;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        hideToolbar();
        DatePicker();
    }


    @OnClick({R.id.fan, R.id.wancheng,R.id.time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fan:
                finish();
                break;
            case R.id.wancheng:
                getxgrefit();
                break;
            case R.id.time:
                customDatePicker.show(now);
                break;
        }
    }
    /**
     * 显示时间
     */
    private void DatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        //获取当前时间
        now = sdf.format(new Date());
        //tvElectricalTime.setText(now.split(" ")[0]);
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {


            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                Log.d("yyyyy", time);
                chushengriqi = time.split(" ")[0];
                times.setText(chushengriqi);

            }
        }, "1990-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    private void getxgrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        TongyongBean tongyongBean = new TongyongBean();

        TongyongBean.ParamBean paramBean = new TongyongBean.ParamBean();
        paramBean.setName(nr.getText().toString());
        paramBean.setPlanTime(times.getText().toString());
        paramBean.setType(6);
        tongyongBean.setParam(paramBean);

        Call<WuBean> gethomegai = RetrofitUtils.getretrofit().gethomegai(result, BaseJsonParam.create(JsonParamUtils.gethomeuser(tongyongBean)));
        gethomegai.enqueue(new Callback<WuBean>() {
            @Override
            public void onResponse(Call<WuBean> call, Response<WuBean> response) {
                // WuBean body = response.body();
                //MyToast.show("添加成功");
                finish();
            }

            @Override
            public void onFailure(Call<WuBean> call, Throwable throwable) {

            }
        });
    }

}
