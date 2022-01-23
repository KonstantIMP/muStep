package org.kimp.mu.step.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

/**
 * Class for caching images
 */
class ImageCache extends LruCache<String, Bitmap> {
    /**
     * Init the image cache
     * @param cacheSize Max image cache size
     */
    public ImageCache(int cacheSize) {
        super(cacheSize);
    }
}
