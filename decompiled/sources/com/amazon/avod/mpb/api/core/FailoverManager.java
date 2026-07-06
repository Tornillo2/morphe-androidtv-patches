package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.sample.DiagnosticInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface FailoverManager {
    @Nullable
    MediaPipelineBackendResultCode evaluateFatal(@NotNull MediaPipelineBackendResultCode mediaPipelineBackendResultCode);

    @Nullable
    MediaPipelineBackendResultCode evaluatePerformance(@NotNull DiagnosticInfo diagnosticInfo);

    boolean isHardwareStackBlocked();

    boolean isHfrBlocked();

    void onSessionReset();

    void onSessionSuccess();

    void setContentFrameRate(float f);

    void setPlaybackSpeed(float f);
}
