package com.yd.ye.main.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.gyf.immersionbar.ImmersionBar;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.umeng.commonsdk.UMConfigure;
import com.xzq.module_base.api.ApiService;
import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.bean.GgBean;
import com.xzq.module_base.bean.GuanggaoBean;
import com.xzq.module_base.bean.HomeqBean;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.bean.TuppianBean;
import com.xzq.module_base.bean.UserInfo;
import com.xzq.module_base.bean.XiugBean;
import com.xzq.module_base.config.Config;
import com.xzq.module_base.managers.GlideEngine;
import com.xzq.module_base.managers.LubanManager;
import com.xzq.module_base.mvp.BaseJsonParam;
import com.xzq.module_base.utils.CommUtils;
import com.xzq.module_base.utils.GlideUtils;
import com.xzq.module_base.utils.JsonParamUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.PermissionUtil;
import com.xzq.module_base.utils.RetrofitUtils;
import com.xzq.module_base.views.DrawableTextView;
import com.yd.ye.R;
import com.yd.ye.main.biz.BaoztActivity;
import com.yd.ye.main.biz.JInianriActivity;
import com.yd.ye.main.biz.TaiActivity;
import com.yd.ye.main.biz.TizhongActivity;
import com.yd.ye.main.biz.WebUrlActivity;
import com.yd.ye.main.biz.XiangceActivity;
import com.yd.ye.main.chansj.TTAdManagerHolder;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * HomeFragment
 * Created by xzq on 2020/8/4.
 */
public class HomeFragment extends BasePresenterFragment {


    @BindView(R.id.bj)
    ImageView bj;
    @BindView(R.id.zhuangtai)
    DrawableTextView zhuangtai;
    @BindView(R.id.beijing)
    DrawableTextView beijing;
    @BindView(R.id.tx)
    ImageView tx;
    @BindView(R.id.yunqi)
    TextView yunqi;
    @BindView(R.id.juchan)
    TextView juchan;
    @BindView(R.id.wq)
    ImageView wq;
    @BindView(R.id.xiangce)
    RelativeLayout xiangce;
    @BindView(R.id.wq1)
    ImageView wq1;
    @BindView(R.id.riji)
    RelativeLayout riji;
    @BindView(R.id.wq2)
    ImageView wq2;
    @BindView(R.id.jilu)
    RelativeLayout jilu;
    @BindView(R.id.wq3)
    ImageView wq3;
    @BindView(R.id.taijiao)
    RelativeLayout taijiao;
    @BindView(R.id.overscroll)
    TwinklingRefreshLayout overscroll;
    @BindView(R.id.wx)
    FrameLayout wx;
    private String token;
    private TTNativeExpressAd mTTAd;
    private TTAdNative mTTAdNative;
    private long startTime;
    private PopupWindow popupWindow;
    private SharedPreferences.Editor editor;
    private String path;
    private String url;

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ImmersionBar.with(this).fitsSystemWindows(false)
                .statusBarColor(R.color.transparent)
                .statusBarDarkFont(false).init();
        //????????????
        overscroll.setPureScrollModeOn();
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String result = token.getString("result", null);
        if(result!=null){
            guanggao(result);
        }


        //?????????????????????
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("share", getContext().MODE_PRIVATE);
        //??????????????????????????????????????????????????????????????????true
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        editor = sharedPreferences.edit();
        if (isFirstRun) {
            Pupupwindow();
        }


    }

    //??????????????????
    public void Homefind() {
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String result = token.getString("result", null);
        Call<HomeqBean> getfind = RetrofitUtils.getretrofit().getfind(result);
        getfind.enqueue(new Callback<HomeqBean>() {
            @Override
            public void onResponse(Call<HomeqBean> call, Response<HomeqBean> response) {
                HomeqBean body = response.body();
                HomeqBean.ResultBean result1 = body.getResult();
                if (result1 != null) {
                    String address = result1.getAddress();

                    yunqi.setText(address);


                    int birthDate = result1.getBirthDate();
                    String s = String.valueOf(birthDate);
                    if (s.length() > 6) {

                        StringBuffer sb = new StringBuffer();
                        StringBuffer insert = sb.append(s).insert(4, "-");
                        StringBuffer insert1 = insert.insert(7, "-");
                        juchan.setText(insert1);
                    } else {
                        juchan.setText("???????????????" + s + "???");
                    }

                    String headImgUrl = result1.getHeadImgUrl();
                    GlideUtils.loadHead(bj, headImgUrl);
                }


            }

            @Override
            public void onFailure(Call<HomeqBean> call, Throwable t) {

            }
        });
    }


    private void guanggao(String result) {

        GgBean ggBean = new GgBean();
        GgBean.ParamBean param = ggBean.getParam();
        ggBean.setParam(param);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<GuanggaoBean> guanggaoBeanCall = service.getgg(result, BaseJsonParam.create(JsonParamUtils.ggjson(ggBean)));
        guanggaoBeanCall.enqueue(new Callback<GuanggaoBean>() {
            @Override
            public void onResponse(Call<GuanggaoBean> call, Response<GuanggaoBean> response) {
                GuanggaoBean body = response.body();
                GuanggaoBean.ResultBean result = body.getResult();
                // VersionUpdataResponse.VersionUpdataInfo result = response.getResult();
                if (result != null) {
                    String appConfig = result.getAppConfig();
                    if (!TextUtils.isEmpty(appConfig)) {
                        //AppConfigBean appConfigBean = JSON.parseObject(appConfig, AppConfigBean.class);
                        String startAdFlag = result.getStartAdFlag();
                        //  String startAdFlag = appConfigBean.getStartAdFlag();
                        //NONE ???  CSJ ?????????  SYS ?????????
                        if ("NONE".equals(startAdFlag)) {
                            // goToMainActivity();
                        } else if ("CSJ".equals(startAdFlag)) {
                            csjSplash();

                        } else if ("SYS".equals(startAdFlag)) {
//                            getTheme().applyStyle(R.style.MySplashTheme, true);
//                            mSplashContainer.removeAllViews();

//                                View inflate = FrameLayout.inflate(mContext, R.layout.activity_splash_my, null);
//                                ImageView img_logo = inflate.findViewById(R.id.img_logo);
//                                mSplashContainer.addView(inflate);


//                                adType = result.getAdType();// ????????????
//                                adImgUrl = result.getAdImgUrl();
//                                adResUrl = result.getAdResUrl();
//
//                                Util.displayBlendImgView(mContext, img_logo, adImgUrl, R.mipmap.ic_logo);

//                        public static void displayBlendImgView(Context context, ImageView view, String imgUrl, int img_dt) {
//                            Glide.with(context)
//                                    .load(imgUrl)
//                                    .placeholder(img_dt)
//                                    .into(view);
//                        }
                            try {
                                Thread.sleep(3000);
                                // goToMainActivity();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            // goToMainActivity();
                        }
                    } else {
                        //  goToMainActivity();
                    }
                } else {
                    // goToMainActivity();
                }
            }

            @Override
            public void onFailure(Call<GuanggaoBean> call, Throwable t) {

            }
        });
    }


    /**
     * ?????????
     */
    private void csjSplash() {
        //step2:??????TTAdNative??????
        mTTAdNative = TTAdSdk.getAdManager().createAdNative(getContext());

        //??????????????????
        loadInteractionAd();
    }

    /**
     * ??????????????????
     */
    private void loadInteractionAd() {
        //step4:????????????????????????AdSlot,???????????????setNativeAdtype???????????????????????????????????????
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(Config.CSJ_CODE_ID_CHAPING)
                .setSupportDeepLink(true)
                .setAdCount(1) //?????????????????????1???3???
                .setExpressViewAcceptedSize(300, 300)//?????????????????????dp???
                .setNativeAdType(AdSlot.TYPE_INTERACTION_AD)//?????????????????????????????????????????????????????????????????????TYPE_BANNER???TYPE_INTERACTION_AD
                .build();

        //step5:??????????????????????????????????????????????????????
        mTTAdNative.loadInteractionExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {


            //??????????????????
            @Override
            public void onError(int code, String message) {
                System.out.println("load error : " + code + ", " + message);
            }

            //??????????????????
            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {

                if (ads == null || ads.size() == 0) {
                    return;
                }
                mTTAd = ads.get(0);
                bindAdListener(mTTAd);
                startTime = System.currentTimeMillis();
                showAd();
            }
        });

    }

    private void showAd() {
        if (mTTAd != null) {
            mTTAd.render();
        } else {
            System.out.println("??????????????????");
        }
    }

    boolean mHasShowDownloadActive = false;

    private void bindAdListener(TTNativeExpressAd ad) {
        ad.setExpressInteractionListener(new TTNativeExpressAd.AdInteractionListener() {
            @Override
            public void onAdDismiss() {
                System.out.println("????????????");
            }

            @Override
            public void onAdClicked(View view, int type) {
                System.out.println("???????????????");
            }

            @Override
            public void onAdShow(View view, int type) {
                System.out.println("????????????");
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                Log.e("ExpressView", "render fail:" + (System.currentTimeMillis() - startTime));
                System.out.println(msg + " code:" + code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                Log.e("ExpressView", "render suc:" + (System.currentTimeMillis() - startTime));
                //??????view????????? ?????? dp
                System.out.println("????????????");
                mTTAd.showInteractionExpressAd(getActivity());
            }
        });
//        bindDislike(ad, false);
        if (ad.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
            return;
        }
        ad.setDownloadListener(new TTAppDownloadListener() {
            @Override
            public void onIdle() {
                System.out.println("??????????????????");
            }

            @Override
            public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                if (!mHasShowDownloadActive) {
                    mHasShowDownloadActive = true;
                    System.out.println("????????????????????????");
                }
            }

            @Override
            public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
                System.out.println("???????????????????????????");
            }

            @Override
            public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
                System.out.println("?????????????????????????????????");
            }

            @Override
            public void onInstalled(String fileName, String appName) {
                System.out.println("?????????????????????????????????");
            }

            @Override
            public void onDownloadFinished(long totalBytes, String fileName, String appName) {
                System.out.println("????????????");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Homefind();
    }

    @OnClick({R.id.bj, R.id.zhuangtai, R.id.beijing, R.id.tx, R.id.xiangce, R.id.riji, R.id.jilu, R.id.taijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuangtai:

                //????????????
                Intent intent = new Intent(getContext(), BaoztActivity.class);
                startActivity(intent);
                break;
            case R.id.beijing:


                //??????
                PictureSelector.create(me)
                        .openGallery(PictureMimeType.ofImage())
                        .imageSpanCount(4)
                        .selectionMode(PictureConfig.SINGLE)
                        .showCropGrid(true)
                        .isEnableCrop(true)
                        .freeStyleCropEnabled(false)
                        .imageEngine(GlideEngine.createGlideEngine())
                        .withAspectRatio(1, 1)
                        //.forResult(PictureConfig.CHOOSE_REQUEST);
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                //????????????
                                path = result.get(0).getCutPath();
                                Image(path);
                            }

                            @Override
                            public void onCancel() {
                                //??????
                            }
                        });


                break;
            case R.id.tx:
                break;
            case R.id.xiangce:
                //????????????
                Intent intent1 = new Intent(getContext(), XiangceActivity.class);
                startActivity(intent1);
                break;
            case R.id.riji:
                //????????????
                Intent intent2 = new Intent(getContext(), JInianriActivity.class);
                startActivity(intent2);
                break;
            case R.id.jilu:
                //????????????
                Intent intent4 = new Intent(getContext(), TizhongActivity.class);
                startActivity(intent4);
                break;
            case R.id.taijiao:
                //????????????
                Intent intent3 = new Intent(getContext(), TaiActivity.class);
                startActivity(intent3);
                break;
        }
    }


    public void Image(String localImgPath) {
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String tok = token.getString("result", null);
        LubanManager.compressSingle(localImgPath, result -> {
            for (String p : result) {
                File file = new File(p);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                Call<TuppianBean> image = RetrofitUtils.getretrofit().getImage(tok, body);
                image.enqueue(new Callback<TuppianBean>() {
                    @Override
                    public void onResponse(Call<TuppianBean> call, Response<TuppianBean> response) {
                        MyToast.show("????????????");
                        TuppianBean body1 = response.body();

                        url = body1.getResult().getUrl();
                        GlideUtils.loadHead(bj, url);
                        homeEditrefit();
                    }

                    @Override
                    public void onFailure(Call<TuppianBean> call, Throwable throwable) {
                        MyToast.show("????????????");
                    }
                });

            }
        });
    }

    private void homeEditrefit() {
        SharedPreferences token = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
        String tok = token.getString("result", null);
        XiugBean xiugBean = new XiugBean();
        XiugBean.ParamBean paramBean = new XiugBean.ParamBean();
        paramBean.setHeadImgUrl(url);
        xiugBean.setParam(paramBean);
        Call<TokenBean> getedit = RetrofitUtils.getretrofit().getedit(tok, BaseJsonParam.create(JsonParamUtils.getxiu(xiugBean)));
        getedit.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {

            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {

            }
        });

    }


    @SuppressLint("ResourceAsColor")
    private void Pupupwindow() {
        View view = LayoutInflater.from(me).inflate(R.layout.layout_first, null);
        TextView no = view.findViewById(R.id.zaixiangxiang);
        TextView ok = view.findViewById(R.id.tongyi);
        TextView tongyi = view.findViewById(R.id.tongyis);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(wx, Gravity.CENTER, 0, 0);

        SpannableStringBuilder spannable = new SpannableStringBuilder("?????????????????????????????????????????????????????????????????????App?????????????????????????????????????????????????????????????????????????????????");
        //???????????????????????????2???4?????????????????????????????????????????????????????????
        spannable.setSpan(new ForegroundColorSpan(R.color.zhihu_item_checkCircle_backgroundColor), 39, 45, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(R.color.zhihu_item_checkCircle_backgroundColor), 46, 52, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //???????????????????????????????????????????????????ResUtil.getColor(R.color.zhihu_item_checkCircle_backgroundColor)
        tongyi.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //????????????
                Intent intent7 = new Intent(getContext(), WebUrlActivity.class);
                intent7.putExtra("url", "file:///android_asset/UserProtocol.html");
                intent7.putExtra("title", "????????????");
                startActivity(intent7);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);// ??????????????????
                ds.setUnderlineText(false);// ???????????????
                ds.clearShadowLayer();
            }
        }, 39, 45, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //????????????
                Intent intent7 = new Intent(getContext(), WebUrlActivity.class);
                intent7.putExtra("url", "file:///android_asset/PrivacyAgreement.html");
                intent7.putExtra("title", "????????????");
                startActivity(intent7);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(R.color.zhihu_item_checkCircle_backgroundColor);// ??????????????????
                ds.setUnderlineText(false);// ???????????????
                ds.clearShadowLayer();
            }
        }, 46, 52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tongyi.setText(spannable);


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                getActivity().finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //?????????SDK?????????
                TTAdManagerHolder.init(getContext());
// ???????????????
                UMConfigure.init(getContext(), UMConfigure.DEVICE_TYPE_PHONE, "");



                //???????????????
                String uniqueID = CommUtils.getUniqueID(getContext());
                //??????
                UserInfo userInfo = new UserInfo();
                UserInfo.ParamDTO paramDTO = new UserInfo.ParamDTO();
                paramDTO.setAppId("wx0fdF8BnfD485Bwp");
                paramDTO.setChannel(null);
                paramDTO.setType(3);
                paramDTO.setUnionInfo(uniqueID);//otFsw6rcDzgU3wcjc3dLl0i04Zrk
                userInfo.setParam(paramDTO);


                Call<TokenBean> getlogin = RetrofitUtils.getretrofit().getlogin(BaseJsonParam.create(JsonParamUtils.getlogin(userInfo)));
                getlogin.enqueue(new Callback<TokenBean>() {
                    @Override
                    public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                        //token
                        token = response.body().getResult();
                        SharedPreferences mySharedPreferences = getContext().getSharedPreferences("token", getContext().MODE_PRIVATE);
                        SharedPreferences.Editor edit = mySharedPreferences.edit();
                        edit.putString("result", token);
                        edit.commit();
                       guanggao(token);
                        Homefind();

                    }

                    @Override
                    public void onFailure(Call<TokenBean> call, Throwable throwable) {

                    }
                });

                editor.putBoolean("isFirstRun", false);//???????????????????????????false???
                //???????????????????????????
                editor.commit();
                PermissionUtil.requestLocationAndStorage(() -> {

                });
                popupWindow.dismiss();
            }
        });
    }

}
