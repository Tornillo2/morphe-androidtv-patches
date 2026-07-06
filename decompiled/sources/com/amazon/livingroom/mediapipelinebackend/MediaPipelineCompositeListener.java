package com.amazon.livingroom.mediapipelinebackend;

import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPipelineCompositeListener extends MediaPipelineListener {

    @NotNull
    public final LinkedHashSet<MediaPipelineListener> listeners = new LinkedHashSet<>();

    public final void addListener(@NotNull MediaPipelineListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.listeners.add(listener)) {
            return;
        }
        MpbLog.i("Listener already registered so refusing to add: " + listener);
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void init() {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.init();
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onBufferUnderrun() {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.onBufferUnderrun();
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onError(int i, @NotNull String message, boolean z, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.onError(i, message, z, th);
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onPlaybackPositionUpdate(long j) {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.onPlaybackPositionUpdate(j);
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onSurfaceSet(boolean z) {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.onSurfaceSet(z);
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void pause() {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.pause();
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void play() {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.play();
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void seek(long j) {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.seek(j);
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void shutdown() {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.shutdown();
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void stop() {
        Iterator<MediaPipelineListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            MediaPipelineListener next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            next.stop();
        }
    }
}
