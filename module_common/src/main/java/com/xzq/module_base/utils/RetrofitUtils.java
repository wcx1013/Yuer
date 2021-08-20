package com.xzq.module_base.utils;

import com.xzq.module_base.api.ApiService;
import com.xzq.module_base.config.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static ApiService getretrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        return service;
    }
}
