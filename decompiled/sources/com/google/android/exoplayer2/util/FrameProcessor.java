package com.google.android.exoplayer2.util;

import android.content.Context;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.video.ColorInfo;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface FrameProcessor {
    public static final long DROP_OUTPUT_FRAME = -2;
    public static final long RELEASE_OUTPUT_FRAME_IMMEDIATELY = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {
        FrameProcessor create(Context context, Listener listener, List<Effect> list, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z) throws FrameProcessingException;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onFrameProcessingEnded();

        void onFrameProcessingError(FrameProcessingException frameProcessingException);

        void onOutputFrameAvailable(long j);

        void onOutputSizeChanged(int i, int i2);
    }

    Surface getInputSurface();

    int getPendingInputFrameCount();

    void registerInputFrame();

    void release();

    void releaseOutputFrame(long j);

    void setInputFrameInfo(FrameInfo frameInfo);

    void setOutputSurfaceInfo(@Nullable SurfaceInfo surfaceInfo);

    void signalEndOfInput();
}
