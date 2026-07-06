package com.amazon.avod.mpb.media.playback.pipeline;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MediaPipelineStateManager extends AbstractMediaComponent {
    public final boolean canConfigure() {
        return isUnconfigured();
    }

    public final boolean canFlush() {
        return isIdle();
    }

    public final boolean canRelease() {
        return isIdle() || isUnconfigured();
    }

    public final boolean canSetOutputSurface() {
        return !isUnconfigured();
    }

    public final boolean canStart() {
        return isIdle();
    }

    public final boolean canStop() {
        return isRunning();
    }
}
