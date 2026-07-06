package com.google.android.exoplayer2.util;

import android.view.SurfaceView;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface DebugViewProvider {
    public static final DebugViewProvider NONE = new DebugViewProvider$$ExternalSyntheticLambda0();

    /* JADX INFO: renamed from: com.google.android.exoplayer2.util.DebugViewProvider$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ SurfaceView lambda$static$0(int i, int i2) {
            return null;
        }
    }

    @Nullable
    SurfaceView getDebugPreviewSurfaceView(int i, int i2);
}
