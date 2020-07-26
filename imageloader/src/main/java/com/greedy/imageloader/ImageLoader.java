package com.greedy.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.greedy.imageloader.cache.AppDoubleCache;
import com.greedy.imageloader.cache.ImageCache;
import com.greedy.imageloader.models.ResizeOptions;
import com.greedy.imageloader.utils.AppExecutor;
import com.greedy.imageloader.utils.Utility;


public class ImageLoader {

    private static final String TAG = "ImageLoader";
    private final ImageLoaderWrapper wrapper;
    private ImageCache cache;

    private ImageLoader(ImageLoaderWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void setCache(ImageCache imageCache) {
        cache = imageCache;
    }


    /**
     * Updating views for received bitmap.
     */
    private void updateImageView(Bitmap cachedBitmap) {
        if (wrapper.context != null && wrapper.imageView != null && wrapper.imageView.getTag().equals(wrapper.url)) {
            wrapper.imageView.setImageBitmap(null);
            wrapper.imageView.setImageBitmap(cachedBitmap);
        }

    }

    /**
     * Fetching image from cache or from url if not found in cache.
     */
    private void displayImage() {
        cache = AppDoubleCache.findOrCreateCache(wrapper.context);
        final Bitmap cachedBitmap = cache.get(wrapper.url);
        if (cachedBitmap != null) {
            updateImageView(cachedBitmap);
            return;
        }
        AppExecutor.submitTask(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Utility.getBitmapFromURL(wrapper.url);
                if (bitmap != null) {
                    updateImageView(bitmap);
                    if (wrapper.resizeOptions != null)
                        bitmap = Utility.getResizedBitmap(bitmap, wrapper.resizeOptions.getWidth(), wrapper.resizeOptions.getHeight());
                    cache.put(wrapper.url, bitmap);
                } else {
                    if (wrapper.placeholder != null)
                        updateImageView(Utility.drawableToBitmap(wrapper.placeholder));
                }
            }
        });
    }


    public void clearCache() {
        cache.clear();
    }

    public static class ImageLoaderWrapper {

        private Context context;
        private String url;
        private ImageView imageView;
        private Drawable placeholder;
        private ResizeOptions resizeOptions;

        public ImageLoaderWrapper with(Context context) {
            this.context = context;
            return this;
        }

        public ImageLoaderWrapper load(String url) {
            this.url = url;
            return this;
        }

        public ImageLoaderWrapper resize(ResizeOptions resizeOptions) {
            this.resizeOptions = resizeOptions;
            return this;

        }

        public ImageLoaderWrapper placeholder(Drawable placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public void into(ImageView imageView) {
            this.imageView = imageView;
            this.imageView.setTag(url);
            validateBuilder(this);
            ImageLoader imageLoader = new ImageLoader(this);
            imageLoader.displayImage();
        }

        /**
         * Add mandatory validations to avoid crashes
         */
        private void validateBuilder(ImageLoaderWrapper builder) {
            if (builder.context == null) {
                Log.e(TAG, "context: ");
                return;
            }

            if (builder.imageView == null) {
                Log.e(TAG, "imageView: ");
                return;
            }
            if (builder.url == null) {
                Log.e(TAG, "url: ");
                return;
            }
        }
    }
}
