package com.giftedcat.picture.lib.photoview.transfer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylib.R;
import com.giftedcat.picture.lib.photoview.loader.ImageLoader;
import com.giftedcat.picture.lib.photoview.style.IIndexIndicator;
import com.giftedcat.picture.lib.photoview.style.IProgressIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transferee Attributes
 * <p>
 * Created by hitomi on 2017/1/19.
 * <p>
 * email: 196425254@qq.com
 */
public final class TransferConfig {
    private int nowThumbnailIndex;
    private int offscreenPageLimit;
    private int missPlaceHolder;
    private int errorPlaceHolder;
    private int backgroundColor;
    private int max;
    private long duration;
    private boolean justLoadHitImage;
    private boolean enableDragClose;

    private Drawable missDrawable;
    private Drawable errorDrawable;

    private List<ImageView> originImageList;
    private List<String> sourceImageList;
    private List<String> thumbnailImageList;

    private IProgressIndicator progressIndicator;
    private IIndexIndicator indexIndicator;
    private ImageLoader imageLoader;

    private @IdRes
    int imageId;
    private ImageView imageView;
    private AbsListView listView;
    private RecyclerView recyclerView;
    private View customView;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    private Transferee.OnTransfereeLongClickListener longClickListener;

    public static Builder build() {
        return new Builder();
    }

    public int getNowThumbnailIndex() {
        return nowThumbnailIndex;
    }

    public void setNowThumbnailIndex(int nowThumbnailIndex) {
        this.nowThumbnailIndex = nowThumbnailIndex;
    }

    public int getOffscreenPageLimit() {
        return offscreenPageLimit;
    }

    public void setOffscreenPageLimit(int offscreenPageLimit) {
        this.offscreenPageLimit = offscreenPageLimit;
    }

    public int getMissPlaceHolder() {
        return missPlaceHolder;
    }

    public void setMissPlaceHolder(int missPlaceHolder) {
        this.missPlaceHolder = missPlaceHolder;
    }

    public int getErrorPlaceHolder() {
        return errorPlaceHolder;
    }

    public void setErrorPlaceHolder(int errorPlaceHolder) {
        this.errorPlaceHolder = errorPlaceHolder;
    }

    public int getBackgroundColor() {
        return backgroundColor == 0 ? Color.BLACK : backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isJustLoadHitImage() {
        return justLoadHitImage;
    }

    public void setJustLoadHitImage(boolean justLoadHitImage) {
        this.justLoadHitImage = justLoadHitImage;
    }


    public boolean isEnableDragClose() {
        return enableDragClose;
    }

    public void enableDragClose(boolean enableDragClose) {
        this.enableDragClose = enableDragClose;
    }

    public Drawable getMissDrawable(Context context) {
        if (missDrawable != null)
            return missDrawable;
        else if (missPlaceHolder != 0)
            return context.getResources().getDrawable(missPlaceHolder);
        else
            return context.getResources().getDrawable(R.drawable.ic_empty_photo);
    }

    public void setMissDrawable(Drawable missDrawable) {
        this.missDrawable = missDrawable;
    }

    public Drawable getErrorDrawable(Context context) {
        if (errorDrawable != null)
            return errorDrawable;
        else if (errorPlaceHolder != 0)
            return context.getResources().getDrawable(errorPlaceHolder);
        else
            return context.getResources().getDrawable(R.drawable.ic_empty_photo);
    }

    public void setErrorDrawable(Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
    }

    List<ImageView> getOriginImageList() {
        return originImageList == null ? new ArrayList<ImageView>() : originImageList;
    }

    void setOriginImageList(List<ImageView> originImageList) {
        this.originImageList = originImageList;
    }

    public List<String> getSourceImageList() {
        return sourceImageList;
    }

    public void setSourceImageList(List<String> sourceImageList) {
        this.sourceImageList = new ArrayList<>();
        this.sourceImageList.addAll(sourceImageList);
        if (this.sourceImageList.size() != getMax()) {
            //??????????????????????????????
            this.sourceImageList.remove(this.sourceImageList.size() - 1);
        }
    }

    public List<String> getThumbnailImageList() {
        return thumbnailImageList;
    }

    public void setThumbnailImageList(List<String> thumbnailImageList) {
        this.thumbnailImageList = thumbnailImageList;
    }

    public IProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public void setProgressIndicator(IProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    public IIndexIndicator getIndexIndicator() {
        return indexIndicator;
    }

    public void setIndexIndicator(IIndexIndicator indexIndicator) {
        this.indexIndicator = indexIndicator;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public Transferee.OnTransfereeLongClickListener getLongClickListener() {
        return longClickListener;
    }

    public void setLongClickListener(Transferee.OnTransfereeLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    /**
     * ??????????????????????????????
     *
     * @return true : ???
     */
    public boolean isSourceEmpty() {
        return sourceImageList == null || sourceImageList.isEmpty();
    }

    /**
     * ?????????????????????????????????
     *
     * @return true : ???
     */
    public boolean isThumbnailEmpty() {
        return thumbnailImageList == null || thumbnailImageList.isEmpty();
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public AbsListView getListView() {
        return listView;
    }

    public void setListView(AbsListView listView) {
        this.listView = listView;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public View getCustomView() {
        return customView;
    }

    public void setCustomView(View customView) {
        this.customView = customView;
    }

    public static class Builder {
        private int nowThumbnailIndex;
        private int offscreenPageLimit;
        private int missPlaceHolder;
        private int errorPlaceHolder;
        private int backgroundColor;
        private long duration;
        private boolean justLoadHitImage;
        private boolean enableDragClose = true;

        private Drawable missDrawable;
        private Drawable errorDrawable;

        private List<String> sourceImageList;
        private List<String> thumbnailImageList;

        private IProgressIndicator progressIndicator;
        private IIndexIndicator indexIndicator;
        private ImageLoader imageLoader;
        private View customView;

        private @IdRes
        int imageId;
        private ImageView imageView;
        private AbsListView listView;
        private RecyclerView recyclerView;

        private Transferee.OnTransfereeLongClickListener longClickListener;

        /**
         * ??????????????????????????????????????????
         */
        public Builder setNowThumbnailIndex(int nowThumbnailIndex) {
            this.nowThumbnailIndex = nowThumbnailIndex;
            return this;
        }

        /**
         * <p>ViewPager ???????????????????????????????????? : ??????????????????????????????????????????????????????</p>
         * <p>?????????1, ?????????????????????3???(nowThumbnailIndex, nowThumbnailIndex
         * + 1, nowThumbnailIndex - 1);?????? 2, ????????????5??????????????????</p>
         * <p>?????????????????????????????????????????????????????????????????????????????????????????????????????????
         * ??????????????????????????????</p>
         */
        public Builder setOffscreenPageLimit(int offscreenPageLimit) {
            this.offscreenPageLimit = offscreenPageLimit;
            return this;
        }

        /**
         * ??????????????????(??????ID)
         */
        public Builder setMissPlaceHolder(int missPlaceHolder) {
            this.missPlaceHolder = missPlaceHolder;
            return this;
        }

        /**
         * ?????????????????????????????????(??????ID)
         */
        public Builder setErrorPlaceHolder(int errorPlaceHolder) {
            this.errorPlaceHolder = errorPlaceHolder;
            return this;
        }

        /**
         * ??? transferee ????????????????????????
         */
        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        /**
         * ??????????????????
         */
        public Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        /**
         * ????????????????????????????????????
         */
        public Builder setJustLoadHitImage(boolean justLoadHitImage) {
            this.justLoadHitImage = justLoadHitImage;
            return this;
        }

        /**
         * ????????????????????????
         */
        public Builder enableDragClose(boolean enableDragClose) {
            this.enableDragClose = enableDragClose;
            return this;
        }

        /**
         * ??????????????????(Drawable ??????)
         */
        public Builder setMissDrawable(Drawable missDrawable) {
            this.missDrawable = missDrawable;
            return this;
        }

        /**
         * ?????????????????????????????????(Drawable ??????)
         */
        public Builder setErrorDrawable(Drawable errorDrawable) {
            this.errorDrawable = errorDrawable;
            return this;
        }

        /**
         * ????????????????????????
         */
        public Builder setSourceImageList(List<String> sourceImageList) {
            this.sourceImageList = sourceImageList;
            return this;
        }

//        /**
//         * ?????????????????????
//         */
//        public Builder setThumbnailImageList(List<String> thumbnailImageList) {
//            this.thumbnailImageList = thumbnailImageList;
//            return this;
//        }

        /**
         * ??????????????????????????? (???????????? ProgressPieIndicator), ????????????
         * IProgressIndicator ??????????????????????????????????????????
         */
        public Builder setProgressIndicator(IProgressIndicator progressIndicator) {
            this.progressIndicator = progressIndicator;
            return this;
        }

        /**
         * ????????????????????? (???????????? IndexCircleIndicator), ????????????
         * IIndexIndicator ??????????????????????????????????????????
         */
        public Builder setIndexIndicator(IIndexIndicator indexIndicator) {
            this.indexIndicator = indexIndicator;
            return this;
        }

        /**
         * ??????????????? (???????????? GlideImageLoader), ????????????
         * ImageLoader ????????????????????????????????????
         */
        public Builder setImageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
            return this;
        }

        /**
         * ?????? transferee ?????????????????????
         */
        public Builder setOnLongClcikListener(Transferee.OnTransfereeLongClickListener listener) {
            this.longClickListener = listener;
            return this;
        }

        /**
         * ??? transferee ????????????????????????????????????
         */
        public Builder setCustomView(View customView) {
            this.customView = customView;
            return this;
        }

        /**
         * ?????? ListView
         *
         * @param imageId item layout ?????? ImageView Resource ID
         */
        public TransferConfig bindListView(AbsListView listView, int imageId) {
            this.listView = listView;
            this.imageId = imageId;
            return create();
        }

        /**
         * ?????? RecyclerView
         *
         * @param imageId item layout ?????? ImageView Resource ID
         */
        public TransferConfig bindRecyclerView(RecyclerView recyclerView, int imageId) {
            this.recyclerView = recyclerView;
            this.imageId = imageId;
            return create();
        }

        /**
         * ???????????? ImageView, ???????????? sourceImageList ??????????????????????????????
         */
        public TransferConfig bindImageView(ImageView imageView, List<String> sourceImageList) {
            this.imageView = imageView;
            this.sourceImageList = sourceImageList;
            return create();
        }

        /**
         * ???????????? ImageView, ?????????????????? url
         */
        public TransferConfig bindImageView(ImageView imageView, String sourceUrl) {
            this.imageView = imageView;
            this.sourceImageList = new ArrayList<>();
            sourceImageList.add(sourceUrl);
            return create();
        }

        private TransferConfig create() {

            TransferConfig config = new TransferConfig();

            config.setNowThumbnailIndex(nowThumbnailIndex);
            config.setOffscreenPageLimit(offscreenPageLimit);
            config.setMissPlaceHolder(missPlaceHolder);
            config.setErrorPlaceHolder(errorPlaceHolder);
            config.setBackgroundColor(backgroundColor);
            config.setDuration(duration);
            config.setJustLoadHitImage(justLoadHitImage);
            config.enableDragClose(enableDragClose);

            config.setMissDrawable(missDrawable);
            config.setErrorDrawable(errorDrawable);

            config.setSourceImageList(sourceImageList);
//            config.setThumbnailImageList(thumbnailImageList);

            config.setProgressIndicator(progressIndicator);
            config.setIndexIndicator(indexIndicator);
            config.setImageLoader(imageLoader);
            config.setCustomView(customView);

            config.setImageId(imageId);
            config.setImageView(imageView);
            config.setListView(listView);
            config.setRecyclerView(recyclerView);

            config.setLongClickListener(longClickListener);

            return config;
        }
    }
}
