package com.amazon.ignitionshared;

import android.view.Surface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface Renderer {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface StateListener {
        void onRenderingPaused();

        void onRenderingPausing();

        void onRenderingStarted();

        void onRenderingStartedUnfocused();

        void onSurfaceChangeFinish();
    }

    void exitRendering();

    int initializeRendering(@NotNull StateListener stateListener, long j);

    void pauseRendering();

    void resumeRendering(boolean z, @Nullable Surface surface);

    void updateIgniteSurfaceRef(@Nullable Surface surface);
}
