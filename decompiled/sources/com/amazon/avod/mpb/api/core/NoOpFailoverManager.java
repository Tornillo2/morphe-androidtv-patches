package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.sample.DiagnosticInfo;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NoOpFailoverManager implements FailoverManager {

    @NotNull
    public static final NoOpFailoverManager INSTANCE = new NoOpFailoverManager();
    public static final boolean isHardwareStackBlocked = false;
    public static final boolean isHfrBlocked = false;

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    @Nullable
    public MediaPipelineBackendResultCode evaluateFatal(@NotNull MediaPipelineBackendResultCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        return null;
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    @Nullable
    public MediaPipelineBackendResultCode evaluatePerformance(@NotNull DiagnosticInfo diagnosticInfo) {
        Intrinsics.checkNotNullParameter(diagnosticInfo, "diagnosticInfo");
        return null;
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    public boolean isHardwareStackBlocked() {
        return isHardwareStackBlocked;
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    public boolean isHfrBlocked() {
        return isHfrBlocked;
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    public void onSessionReset() {
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    public void onSessionSuccess() {
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    public void setContentFrameRate(float f) {
    }

    @Override // com.amazon.avod.mpb.api.core.FailoverManager
    public void setPlaybackSpeed(float f) {
    }
}
