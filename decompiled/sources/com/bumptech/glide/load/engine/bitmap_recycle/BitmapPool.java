package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface BitmapPool {
    void clearMemory();

    @NonNull
    Bitmap get(int i, int i2, Bitmap.Config config);

    @NonNull
    Bitmap getDirty(int i, int i2, Bitmap.Config config);

    long getMaxSize();

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f);

    void trimMemory(int i);
}
