package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ReadyToPlayTracker {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long READY_TO_PLAY_TRACKER_THRESHOLD_MILLIS = 20000;

    @NotNull
    public Runnable delayRunnable;

    @NotNull
    public final Handler handler;

    @NotNull
    public final Listener listener;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onWaitTrackerEvent();
    }

    public ReadyToPlayTracker(@NotNull Handler handler, @NotNull Listener listener) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.handler = handler;
        this.listener = listener;
        this.delayRunnable = new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.ReadyToPlayTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ReadyToPlayTracker.delayRunnable$lambda$0(this.f$0);
            }
        };
    }

    public static final void delayRunnable$lambda$0(ReadyToPlayTracker readyToPlayTracker) {
        readyToPlayTracker.listener.onWaitTrackerEvent();
    }

    public final void startWatcher() {
        stopWatcher();
        this.handler.postDelayed(this.delayRunnable, 20000L);
    }

    public final void stopWatcher() {
        this.handler.removeCallbacks(this.delayRunnable);
    }
}
