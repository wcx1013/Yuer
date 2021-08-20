package com.xzq.module_base.config;

/**
 * 全局配置
 *
 * @author xzq
 */

public interface Config {
    // 缓存加密密码
    String CACHE_PASSWORD = "\u2605\u2721\u2606\u263C\u00A7\u2603";
    // 缓存加密盐
    String CACHE_SALT = "=?UTF-8?B?OSozajloY2VqbjQmZzI4Mg==?=";

    boolean DBG_STATE = false;

    //String BASE_URL = "http://172.16.0.32:8080/";//本地
    String BASE_URL = "http://app.91juguo.com/api/";//正式
   // String BASE_URL = "http://app.91juguo.com/testJ/";//测试

    // 穿山甲
    public static final String CSJ_APP_ID = "5183315";// 应用id
    public static final String CSJ_CODE_ID = "887495772";// 代码位id 开屏广告
    public static final String CSJ_CODE_ID_CHAPING="946257133";//代码位id 插屏广告
}
