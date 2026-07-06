package com.amazon.avod.mpb.api.callback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface SurfaceResizerCallback {
    void commitPendingAspectRatio();

    void resetViewport();

    void setPendingAspectRatio(float f);

    void setViewport(int i, int i2, int i3, int i4);
}
