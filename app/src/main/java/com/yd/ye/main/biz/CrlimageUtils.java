package com.yd.ye.main.biz;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class CrlimageUtils {
    public RequestOptions yuan(int du){
        RoundedCorners roundedCorners= new RoundedCorners(du);
//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners);

        return options;
    }
}
