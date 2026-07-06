package com.amazon.livingroom.mediapipelinebackend;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class MediaPipelineListener {
    public void onError(int i, @NotNull String message, boolean z, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public void init() {
    }

    public void onBufferUnderrun() {
    }

    public void pause() {
    }

    public void play() {
    }

    public void shutdown() {
    }

    public void stop() {
    }

    public void onPlaybackPositionUpdate(long j) {
    }

    public void onSurfaceSet(boolean z) {
    }

    public void seek(long j) {
    }
}
