package com.bumptech.glide.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ViewPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback {
    public int[] size;
    public SizeViewTarget viewTarget;

    public ViewPreloadSizeProvider() {
    }

    @Override // com.bumptech.glide.ListPreloader.PreloadSizeProvider
    @Nullable
    public int[] getPreloadSize(@NonNull T t, int i, int i2) {
        int[] iArr = this.size;
        if (iArr == null) {
            return null;
        }
        return Arrays.copyOf(iArr, iArr.length);
    }

    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int i, int i2) {
        this.size = new int[]{i, i2};
        this.viewTarget = null;
    }

    public void setView(@NonNull View view) {
        if (this.size == null && this.viewTarget == null) {
            SizeViewTarget sizeViewTarget = new SizeViewTarget(view);
            this.viewTarget = sizeViewTarget;
            sizeViewTarget.getSize(this);
        }
    }

    public ViewPreloadSizeProvider(@NonNull View view) {
        SizeViewTarget sizeViewTarget = new SizeViewTarget(view);
        this.viewTarget = sizeViewTarget;
        sizeViewTarget.getSize(this);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SizeViewTarget extends CustomViewTarget<View, Object> {
        public SizeViewTarget(@NonNull View view) {
            super(view);
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.CustomViewTarget
        public void onResourceCleared(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }
    }
}
