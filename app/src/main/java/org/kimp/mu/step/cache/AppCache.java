package org.kimp.mu.step.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.kimp.mu.step.async.TaskRunner;

import java.io.File;
import java.io.IOException;

/**
 * Class for caching files and data
 * Uses Singleton pattern for full data
 * access across all activities
 */
public class AppCache {
    public static final int IMAGE_CACHE_SIZE = 32 * 1024 * 1024;
    public static final int AUDIO_CACHE_SIZE = 32 * 1024 * 1024;
    public static final int DATA_CACHE_SIZE  = 16 * 1024 * 1024;

    private ImageCache imageCache = null;

    private static AppCache instance = null;

    private AppCache() {
        imageCache = new ImageCache(IMAGE_CACHE_SIZE);
    }

    public static AppCache getInstance() {
        if (instance == null) instance = new AppCache();
        return instance;
    }

    /**
     * Downloads image from firebase storage, caches it
     * and sets to the image view
     * @param firebasePath Path to the image for downloading
     * @param id New id for cached data
     * @param view Image view for image set
     */
    public void setImageFromFirebaseWithCache(String firebasePath, String id, ImageView view) {
        if (getImage(id) != null) {
            view.setImageBitmap(getImage(id));
        }
        else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference pathReference = storage.getReference().child(firebasePath);

            pathReference.getDownloadUrl().addOnSuccessListener(uri -> {
                TaskRunner cacheImage = new TaskRunner();
                cacheImage.executeAsync(() -> Picasso.get().load(uri).get(), (data) -> {
                    putImage(id, data);
                    view.setImageBitmap(data);
                });
            }).addOnFailureListener(e -> Log.e("IMAGE_CACHE", "Failed to download image: " + firebasePath + ", reason: " + e.getMessage()));
        }
    }

    public void putImage(String id, @NonNull Bitmap drawable) {
        imageCache.put(id, drawable);
    }

    public Bitmap getImage(String id) {
        return imageCache.get(id);
    }

    /**
     * Return firstly found cached data(img, audio or text)
     * @param id Id of the cached data
     */
    public Object get(String id) {
        if (imageCache.get(id) != null) return imageCache.get(id);
        return null;
    }
}
