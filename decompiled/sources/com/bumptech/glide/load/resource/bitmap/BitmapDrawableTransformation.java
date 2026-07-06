package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class BitmapDrawableTransformation implements Transformation<BitmapDrawable> {
    public final Transformation<Drawable> wrapped;

    public BitmapDrawableTransformation(Transformation<Bitmap> transformation) {
        this.wrapped = new DrawableTransformation(transformation, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Resource<BitmapDrawable> convertToBitmapDrawableResource(Resource<Drawable> resource) {
        if (resource.get() instanceof BitmapDrawable) {
            return resource;
        }
        throw new IllegalArgumentException("Wrapped transformation unexpectedly returned a non BitmapDrawable resource: " + resource.get());
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof BitmapDrawableTransformation) {
            return this.wrapped.equals(((BitmapDrawableTransformation) obj).wrapped);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.wrapped.hashCode();
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<BitmapDrawable> transform(@NonNull Context context, @NonNull Resource<BitmapDrawable> resource, int i, int i2) {
        Resource resourceTransform = this.wrapped.transform(context, resource, i, i2);
        convertToBitmapDrawableResource(resourceTransform);
        return resourceTransform;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }

    public static Resource<Drawable> convertToDrawableResource(Resource<BitmapDrawable> resource) {
        return resource;
    }
}
