package androidx.media3.common;

import android.view.SurfaceView;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface DebugViewProvider {
    public static final DebugViewProvider NONE = new DebugViewProvider$$ExternalSyntheticLambda0();

    /* JADX INFO: renamed from: androidx.media3.common.DebugViewProvider$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ SurfaceView lambda$static$0(int i, int i2) {
            return null;
        }
    }

    @Nullable
    SurfaceView getDebugPreviewSurfaceView(int i, int i2);
}
