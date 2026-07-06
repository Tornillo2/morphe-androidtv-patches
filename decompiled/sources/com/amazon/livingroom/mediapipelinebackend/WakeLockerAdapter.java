package com.amazon.livingroom.mediapipelinebackend;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WakeLockerAdapter extends MediaPipelineListener {

    @NotNull
    public final WakeLocker wakeLocker;

    public WakeLockerAdapter(@NotNull WakeLocker wakeLocker) {
        Intrinsics.checkNotNullParameter(wakeLocker, "wakeLocker");
        this.wakeLocker = wakeLocker;
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onError(int i, @NotNull String message, boolean z, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.wakeLocker.removeWakeLock(this);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void pause() {
        this.wakeLocker.removeWakeLock(this);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void play() {
        this.wakeLocker.addWakeLock(this);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void shutdown() {
        this.wakeLocker.removeWakeLock(this);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void stop() {
        this.wakeLocker.removeWakeLock(this);
    }
}
