package com.yd.ye.main.biz;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JluActivity extends BasePresenterActivity {
    @BindView(R.id.fans)
    ImageView fans;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.sg)
    EditText sg;
    @BindView(R.id.tz)
    EditText tz;
    @BindView(R.id.bcun)
    ImageView bcun;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jlu;
    }

    @Override
    protected void initViews(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

    }



    @OnClick({R.id.fans, R.id.bcun})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fans:
                finish();
                break;
            case R.id.bcun:
                if(!sg.getText().toString().isEmpty()&&!tz.getText().toString().isEmpty()){
                    Addrefit();
                }

                break;
        }
    }

    private void Addrefit() {
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String result = token.getString("result", null);
        TongyongBean tongyongBean = new TongyongBean();

        TongyongBean.ParamBean paramBean = new TongyongBean.ParamBean();
        paramBean.setRemarks(sg.getText().toString());
        paramBean.setMatching(tz.getText().toString());
        paramBean.setType(7);
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
