package com.bumptech.glide.request.target;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.annotation.NonNull;
import com.bumptech.glide.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class SimpleTarget<Z> extends BaseTarget<Z> {
    public final int height;
    public final int width;

    public SimpleTarget(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
        if (Util.isValidDimensions(this.width, this.height)) {
            sizeReadyCallback.onSizeReady(this.width, this.height);
            return;
        }
        StringBuilder sb = new StringBuilder("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ");
        sb.append(this.width);
        sb.append(" and height: ");
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.height, ", either provide dimensions in the constructor or call override()"));
    }

    public SimpleTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // com.bumptech.glide.request.target.Target
    public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
    }
}
