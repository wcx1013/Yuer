package com.xzq.module_base.api;

import com.xzq.module_base.bean.CiyBean;
import com.xzq.module_base.bean.CuiBean;
import com.xzq.module_base.bean.DeviceDto;
import com.xzq.module_base.bean.GuanggaoBean;
import com.xzq.module_base.bean.HomeqBean;
import com.xzq.module_base.bean.HomereposeBean;
import com.xzq.module_base.bean.LoginDto;
import com.xzq.module_base.bean.PlBean;
import com.xzq.module_base.bean.RakingDto;
import com.xzq.module_base.bean.SceneDto;
import com.xzq.module_base.bean.ScxiangyinBean;
import com.xzq.module_base.bean.ShanBean;
import com.xzq.module_base.bean.SplistBean;
import com.xzq.module_base.bean.TingBean;
import com.xzq.module_base.bean.TokenBean;
import com.xzq.module_base.bean.TuppianBean;
import com.xzq.module_base.bean.WuBean;
import com.xzq.module_base.bean.YeBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * ApiService
 *
 * @author xzq
 */
public interface ApiService {

    @Multipart
    @POST("/qixing-app/app/upload/uploadImg")
    Observable<NetBean<String>> uploadImg(@Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("/qixing-app/app/user/login")
    Observable<NetBean<LoginDto>> login(
            @Field("userName") String userName,
            @Field("password") String password
    );

    @GET("/qixing-app/app/goods/goodsList")
    Observable<NetBean<List<DeviceDto>>> getDevices(@Query("token") String token,
                                                    @Query("page") int page,
                                                    @Query("limit") int limit
    );

    @GET("/qixing-app/app/goods/goodsList")
    Observable<NetBean<List<SceneDto>>> getSceneList(@Query("token") String token,
                                                     @Query("page") int page,
                                                     @Query("limit") int limit
    );

    @GET("/qixing-app/app/goods/goodsList")
    Observable<NetBean<List<RakingDto>>> getRakingGlobal(@Query("token") String token,
                                                         @Query("page") int page,
                                                         @Query("limit") int limit
    );

    @GET("/qixing-app/app/goods/goodsList")
    Observable<NetBean<List<RakingDto>>> getRakingMySelf(@Query("token") String token,
                                                         @Query("page") int page,
                                                         @Query("limit") int limit
    );

    // 登录获取token
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/login")
    Call<TokenBean> getlogin(@Body RequestBody requestBody);

    // 帮助反馈
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("feedback/")
    Call<TokenBean> getfeedBack(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //广告
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("app/v-check")
    Call<GuanggaoBean> getgg(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //育儿页
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("res-ext/list")
    Call<YeBean> getYe(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //上传文件
    @Multipart
    @POST("file/upload")
    Call<TuppianBean> getImage(@Header("Authorization") String Authorization, @Part MultipartBody.Part file);

//批量上传文件
    @Multipart
    @POST("file/batch/import")
    Call<PlBean> getImagelist(@Header("Authorization") String Authorization, @Part ArrayList<MultipartBody.Part> file);

    @Headers("Content-type:application/json; charset=utf-8")
    @POST("res/{resId}/{starType}/")
    Call<TokenBean> getsfsc(@Header("Authorization") String Authorization, @Path("resId") String resId, @Path("starType") int starType);

    //收藏列表
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("res-ext/user/star-list")
    Call<ScxiangyinBean> getsclist(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //首页列表
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/currency/list-pc")
    Call<HomereposeBean> gethomelist(@Header("Authorization") String Authorization, @Body RequestBody requestBody);


    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/currency/batch/delete")
    Call<ShanBean> getdelect(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //首页修改
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/currency/add")
    Call<WuBean> gethomegai(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //首页用户信息查询
    @Headers("Content-type:application/json; charset=utf-8")
    @GET("user/bbxc/info")
    Call<HomeqBean> getfind(@Header("Authorization") String Authorization);

    //修改首页信息
    @Headers("Content-type:application/json; charset=utf-8")
    @PUT("user/me/")
    Call<TokenBean> getedit(@Header("Authorization") String Authorization, @Body RequestBody requestBody);


    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/album/list-pc")
    Call<CuiBean> getwx(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    //添加照片
    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/album/add")
    Call<TokenBean> getaddzp(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    @Headers("Content-type:application/json; charset=utf-8")
    @POST("res-ext/top-list")
    Call <TingBean> getmflikeList(@Header("Authorization") String Authorization, @Body RequestBody requestBody);


    @Headers("Content-type:application/json; charset=utf-8")
    @POST("res-ext/list")
    Call <CiyBean> getmfList(@Header("Authorization") String Authorization, @Body RequestBody requestBody);

    @Headers("Content-type:application/json; charset=utf-8")
    @POST("user/album/batch/delete")
    Call <TokenBean> getshan(@Header("Authorization") String Authorization, @Body RequestBody requestBody);






}
