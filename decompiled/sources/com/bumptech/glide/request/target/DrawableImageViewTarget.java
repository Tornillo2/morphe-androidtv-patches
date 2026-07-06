package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DrawableImageViewTarget extends ImageViewTarget<Drawable> {
    public DrawableImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public DrawableImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Override // com.bumptech.glide.request.target.ImageViewTarget
    public void setResource(@Nullable Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }
}
