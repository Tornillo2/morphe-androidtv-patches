package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class BitmapTransformation implements Transformation<Bitmap> {
    public abstract Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2);

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public final Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i, int i2) {
        if (!Util.isValidDimensions(i, i2)) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline0.m("Cannot apply transformation on width: ", i, " or height: ", i2, " less than or equal to zero and not Target.SIZE_ORIGINAL"));
        }
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Bitmap bitmap = resource.get();
        if (i == Integer.MIN_VALUE) {
            i = bitmap.getWidth();
        }
        if (i2 == Integer.MIN_VALUE) {
            i2 = bitmap.getHeight();
        }
        Bitmap bitmapTransform = transform(bitmapPool, bitmap, i, i2);
        return bitmap.equals(bitmapTransform) ? resource : BitmapResource.obtain(bitmapTransform, bitmapPool);
    }
}
