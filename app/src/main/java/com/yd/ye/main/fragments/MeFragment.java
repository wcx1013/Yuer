package com.yd.ye.main.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.bean.MarketPkgsBean;
import com.xzq.module_base.utils.CommUtils;
import com.xzq.module_base.utils.MyToast;
import com.yd.ye.R;
import com.yd.ye.main.adapter.GwhpPopupwindowAdapter;
import com.yd.ye.main.biz.NoScrollGridView;
import com.yd.ye.main.biz.ShoucActivity;
import com.yd.ye.main.biz.WebUrlActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MeFragment
 * Created by xzq on 2020/8/4.
 */
public class MeFragment extends BasePresenterFragment {

    @BindView(R.id.rl_fxhy)
    RelativeLayout rlFxhy;
    @BindView(R.id.rl_yszc)
    RelativeLayout rlYszc;
    @BindView(R.id.rl_bzfk)
    RelativeLayout rlBzfk;
    @BindView(R.id.rl_gywm)
    RelativeLayout rlGywm;
    @BindView(R.id.rl_gwhp)
    RelativeLayout rlGwhp;
    @BindView(R.id.yonghuxieyi)
    RelativeLayout yonghuxieyi;
    @BindView(R.id.rl_tcdl)
    RelativeLayout rlTcdl;
    @BindView(R.id.img_gx_r)
    ImageView imgGxR;
    @BindView(R.id.tv_vesion)
    TextView tvVesion;
    @BindView(R.id.rl_bbgx)
    RelativeLayout rlBbgx;
//    @BindView(R.id.over_scroll_view)
//    TwinklingRefreshLayout overScrollView;
    private ArrayList<MarketPkgsBean> installedMarketPkgs;
    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
           // overScrollView.setPureScrollModeOn();

    }

    @OnClick({R.id.rl_fxhy, R.id.rl_yszc, R.id.rl_bzfk, R.id.rl_gywm, R.id.rl_gwhp, R.id.yonghuxieyi, R.id.rl_tcdl, R.id.rl_bbgx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_fxhy:
                Intent intent1 = new Intent(getContext(), ShoucActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_yszc:
                Intent intent7 = new Intent(getContext(), WebUrlActivity.class);
                intent7.putExtra("url", "file:///android_asset/PrivacyAgreement.html");
                intent7.putExtra("title", "隐私政策");
                startActivity(intent7);
                break;
            case R.id.rl_bzfk:
                Intent intent8 = new Intent(getContext(), HelpFeedbackActivity.class);
                startActivity(intent8);
                break;
            case R.id.rl_gywm:
                Intent intent = new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_gwhp:
                installedMarketPkgs = CommUtils.getInstalledMarketPkgs(getContext());
                if (installedMarketPkgs != null && installedMarketPkgs.size() > 0) {
                    showSelectDialog();
                } else {
                    // ToastUtils.shortShowStr(this, "手机暂无应用商店");
                    ToastUtils.showShort("手机暂无应用商店");
                }
                break;
            case R.id.yonghuxieyi:
                //用户协议
                Intent intent2 = new Intent(getContext(), WebUrlActivity.class);
                intent2.putExtra("url", "file:///android_asset/UserProtocol.html");
                intent2.putExtra("title", "用户协议");
                startActivity(intent2);
                break;
            case R.id.rl_tcdl:
                //退出登录
                break;
            case R.id.rl_bbgx:
                MyToast.showSucceed("已是最新版本");
                break;
        }
    }

    /**
     * 跳转应用商店弹窗
     */
    private void showSelectDialog() {
        final AlertDialog dialog;
        View diaView = View.inflate(getContext(), R.layout.gwhp_popupwindow, null);
        NoScrollGridView grid_view = diaView.findViewById(R.id.grid_view);

        GwhpPopupwindowAdapter gwhpPopupwindowAdapter = new GwhpPopupwindowAdapter(getContext(), installedMarketPkgs);
        grid_view.setAdapter(gwhpPopupwindowAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(diaView);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        //   Display display = getWindowManager().getDefaultDisplay();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setWindowAnimations(R.style.popupAnimation);
        lp.gravity = Gravity.BOTTOM;
        // lp.width = (int) (display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);

        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 跳转应用商店
                MarketPkgsBean marketPkgsBean = installedMarketPkgs.get(position);
                CommUtils.launchAppDetail(getContext(), CommUtils.getApkPkgName(getContext()), marketPkgsBean.getPkgName());
                dialog.dismiss();
            }
        });
    }

}
