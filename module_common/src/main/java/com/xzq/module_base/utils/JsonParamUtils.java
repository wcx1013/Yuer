package com.xzq.module_base.utils;

import com.google.gson.Gson;
import com.xzq.module_base.bean.AddzpBean;
import com.xzq.module_base.bean.ChanBean;
import com.xzq.module_base.bean.FeedBackBean;
import com.xzq.module_base.bean.GgBean;
import com.xzq.module_base.bean.HomeBean;
import com.xzq.module_base.bean.HscBean;
import com.xzq.module_base.bean.QuBean;
import com.xzq.module_base.bean.ShanBean;
import com.xzq.module_base.bean.ShouqinqiuBean;
import com.xzq.module_base.bean.TongyongBean;
import com.xzq.module_base.bean.TuijianBean;
import com.xzq.module_base.bean.UserInfo;
import com.xzq.module_base.bean.XiugBean;
import com.xzq.module_base.bean.YuerBean;
import com.xzq.module_base.bean.ZyBean;

public class JsonParamUtils {
//    public static String refund(String content,String imagesUrl) {
//        JSONObject postJson = new JSONObject();
//        try {
//            postJson.put("content", content);
//            postJson.put("imagesUrl",imagesUrl);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return postJson.toString();
//    }
public static String getlogin(UserInfo userInfo) {
    Gson gson = new Gson();
    String s = gson.toJson(userInfo);
    return s;
}
public static String feekjson(FeedBackBean feedback) {
    Gson gson = new Gson();
    String s = gson.toJson(feedback);
    return s;
}
    public static String ggjson(GgBean ggBean) {
        Gson gson = new Gson();
        String s = gson.toJson(ggBean);
        return s;
    }
    public static String Yejson(YuerBean yuerBean) {
        Gson gson = new Gson();
        String s = gson.toJson(yuerBean);
        return s;
    }
    public static String getshoulist(ShouqinqiuBean shouqinqiuBean) {
        Gson gson = new Gson();
        String s = gson.toJson(shouqinqiuBean);
        return s;
    }
    public static String gethomelist(HomeBean homeBean) {
        Gson gson = new Gson();
        String s = gson.toJson(homeBean);
        return s;
    }
    public static String getshan(ShanBean shanBean) {
        Gson gson = new Gson();
        String s = gson.toJson(shanBean);
        return s;
    }
    public static String gethomeuser(TongyongBean tongyongBean) {
        Gson gson = new Gson();
        String s = gson.toJson(tongyongBean);
        return s;
    }
    public static String getxiu(XiugBean xiugBean) {
        Gson gson = new Gson();
        String s = gson.toJson(xiugBean);
        return s;
    }
    public static String getxiu(ChanBean chanBean) {
        Gson gson = new Gson();
        String s = gson.toJson(chanBean);
        return s;
    }
    public static String getAddzp(AddzpBean addzpBean) {
        Gson gson = new Gson();
        String s = gson.toJson(addzpBean);
        return s;
    }
    public static String getwxc(QuBean quBean) {
        Gson gson = new Gson();
        String s = gson.toJson(quBean);
        return s;
    }
    public static String mftuijianjson(TuijianBean tuijianBean) {
        Gson gson = new Gson();
        String s = gson.toJson(tuijianBean);
        return s;
    }
    public static String getcy(ZyBean zyBean) {
        Gson gson = new Gson();
        String s = gson.toJson(zyBean);
        return s;
    }
    public static String gethsc(HscBean hscBean) {
        Gson gson = new Gson();
        String s = gson.toJson(hscBean);
        return s;
    }
}
