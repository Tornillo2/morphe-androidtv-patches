package com.google.android.exoplayer2;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.drm.DrmSession;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FormatHolder {

    @Nullable
    public DrmSession drmSession;

    @Nullable
    public Format format;

    public void clear() {
        this.drmSession = null;
        this.format = null;
    }
}
