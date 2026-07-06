package com.amazon.avod.mpb.media.playback.pipeline;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Display;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
public final class VideoFrameReleaseTimeHelper {
    public static final long CHOREOGRAPHER_SAMPLE_DELAY_MILLIS = 500;

    @NotNull
    public static final Companion Companion = new Companion();
    public static final float DEFAULT_REFRESH_RATE = 60.0f;
    public static final long MAX_ALLOWED_DRIFT_NS = 20000000;
    public static final float MAX_REFRESH_RATE = 240.0f;
    public static final int MIN_FRAMES_FOR_ADJUSTMENT = 6;
    public static final long VSYNC_OFFSET_PERCENTAGE = 80;
    public long adjustedLastFrameTimeNs;
    public float currentRefreshRate;

    @NotNull
    public final DisplayManager.DisplayListener displayListener;

    @NotNull
    public final DisplayManager displayManager;
    public long frameCount;

    @Nullable
    public Handler handler;
    public boolean haveSync;
    public long lastFramePresentationTimeUs;

    @NotNull
    public final Object mutex;
    public long pendingAdjustedFrameTimeNs;
    public final boolean shouldEnableAdaptiveRefreshSync;
    public long syncFramePresentationTimeNs;
    public long syncUnadjustedReleaseTimeNs;
    public long vsyncDurationNs;
    public long vsyncOffsetNs;

    @NotNull
    public final VSyncSampler vsyncSampler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final long closestVsync(long j, long j2, long j3) {
            long j4;
            long j5 = (((j - j2) / j3) * j3) + j2;
            if (j <= j5) {
                j4 = j5 - j3;
            } else {
                j4 = j5;
                j5 = j3 + j5;
            }
            return j5 - j < j - j4 ? j5 : j4;
        }

        public final float getDefaultDisplayRefreshRate(Context context) {
            Object systemService = context.getSystemService("display");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
            return sanitizeRefreshRate(((DisplayManager) systemService).getDisplay(0).getRefreshRate());
        }

        public final float sanitizeRefreshRate(float f) {
            if (f > 0.0f) {
                return Math.min(f, 240.0f);
            }
            return 60.0f;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nVideoFrameReleaseTimeHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VideoFrameReleaseTimeHelper.kt\ncom/amazon/avod/mpb/media/playback/pipeline/VideoFrameReleaseTimeHelper$VSyncSampler\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,318:1\n1#2:319\n*E\n"})
    public static final class VSyncSampler implements Choreographer.FrameCallback, Handler.Callback {
        public static final int CREATE_CHOREOGRAPHER = 0;
        public static final int MSG_ADD_OBSERVER = 1;
        public static final int MSG_REMOVE_OBSERVER = 2;
        public Choreographer choreographer;

        @NotNull
        public final Handler handler;
        public volatile long sampledVsyncTimeNs;

        @NotNull
        public static final Companion Companion = new Companion();

        @NotNull
        public static final VSyncSampler instance = new VSyncSampler();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final VSyncSampler getInstance() {
                return VSyncSampler.instance;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public VSyncSampler() {
            HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.handler = handler;
            handler.sendEmptyMessage(0);
        }

        public final void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        public final void addObserverInternal() {
            Choreographer choreographer = this.choreographer;
            if (choreographer != null) {
                choreographer.postFrameCallback(this);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("choreographer");
                throw null;
            }
        }

        public final void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            this.sampledVsyncTimeNs = j;
            Choreographer choreographer = this.choreographer;
            if (choreographer != null) {
                choreographer.postFrameCallbackDelayed(this, 500L);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("choreographer");
                throw null;
            }
        }

        public final long getSampledVsyncTimeNs() {
            return this.sampledVsyncTimeNs;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NotNull Message message) {
            Intrinsics.checkNotNullParameter(message, "message");
            int i = message.what;
            if (i == 0) {
                createChoreographerInstanceInternal();
                return true;
            }
            if (i == 1) {
                addObserverInternal();
                return true;
            }
            if (i != 2) {
                return false;
            }
            removeObserverInternal();
            return true;
        }

        public final void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }

        public final void removeObserverInternal() {
            Choreographer choreographer = this.choreographer;
            if (choreographer != null) {
                choreographer.removeFrameCallback(this);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("choreographer");
                throw null;
            }
        }

        public final void setSampledVsyncTimeNs(long j) {
            this.sampledVsyncTimeNs = j;
        }
    }

    public VideoFrameReleaseTimeHelper(@NotNull Context appContext, @NotNull MPBInternalConfig config) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(config, "config");
        this.shouldEnableAdaptiveRefreshSync = config.getEnableAdaptiveRefreshSync();
        Object systemService = appContext.getSystemService("display");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        this.displayManager = (DisplayManager) systemService;
        VSyncSampler.Companion.getClass();
        this.vsyncSampler = VSyncSampler.instance;
        this.mutex = new Object();
        this.currentRefreshRate = Companion.getDefaultDisplayRefreshRate(appContext);
        long nanos = (long) (TimeUnit.SECONDS.toNanos(1L) / ((double) this.currentRefreshRate));
        this.vsyncDurationNs = nanos;
        this.vsyncOffsetNs = (nanos * 80) / ((long) 100);
        this.displayListener = new DisplayManager.DisplayListener() { // from class: com.amazon.avod.mpb.media.playback.pipeline.VideoFrameReleaseTimeHelper$displayListener$1
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int i) {
                VideoFrameReleaseTimeHelper videoFrameReleaseTimeHelper = this.this$0;
                synchronized (videoFrameReleaseTimeHelper.mutex) {
                    Display display = videoFrameReleaseTimeHelper.displayManager.getDisplay(i);
                    if (display != null) {
                        float fSanitizeRefreshRate = VideoFrameReleaseTimeHelper.Companion.sanitizeRefreshRate(display.getRefreshRate());
                        if (fSanitizeRefreshRate != videoFrameReleaseTimeHelper.currentRefreshRate) {
                            videoFrameReleaseTimeHelper.currentRefreshRate = fSanitizeRefreshRate;
                            MpbLog.logf("VideoFrameReleaseTimeHelper: refresh rate changed to " + fSanitizeRefreshRate + " hz", new Object[0]);
                            long nanos2 = (long) (((double) TimeUnit.SECONDS.toNanos(1L)) / ((double) fSanitizeRefreshRate));
                            videoFrameReleaseTimeHelper.vsyncDurationNs = nanos2;
                            videoFrameReleaseTimeHelper.vsyncOffsetNs = (nanos2 * 80) / ((long) 100);
                        }
                    }
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int i) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int i) {
            }
        };
    }

    public final long adjustReleaseTime(long j, long j2) {
        long j3;
        long j4;
        synchronized (this.mutex) {
            long j5 = ((long) 1000) * j;
            try {
                if (this.haveSync) {
                    if (j != this.lastFramePresentationTimeUs) {
                        this.frameCount++;
                        this.adjustedLastFrameTimeNs = this.pendingAdjustedFrameTimeNs;
                    }
                    long j6 = this.frameCount;
                    if (j6 >= 6) {
                        j4 = this.adjustedLastFrameTimeNs + ((j5 - this.syncFramePresentationTimeNs) / j6);
                        if (isDriftTooLarge(j4, j2)) {
                            this.haveSync = false;
                        } else {
                            j3 = (this.syncUnadjustedReleaseTimeNs + j4) - this.syncFramePresentationTimeNs;
                        }
                    } else if (isDriftTooLarge(j5, j2)) {
                        this.haveSync = false;
                    }
                    j3 = j2;
                    j4 = j5;
                } else {
                    j3 = j2;
                    j4 = j5;
                }
                if (!this.haveSync) {
                    this.syncFramePresentationTimeNs = j5;
                    this.syncUnadjustedReleaseTimeNs = j2;
                    this.frameCount = 0L;
                    this.haveSync = true;
                }
                this.lastFramePresentationTimeUs = j;
                this.pendingAdjustedFrameTimeNs = j4;
                if (this.vsyncSampler.sampledVsyncTimeNs == 0) {
                    return j3;
                }
                return Companion.closestVsync(j3, this.vsyncSampler.sampledVsyncTimeNs, this.vsyncDurationNs) - this.vsyncOffsetNs;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void disable() {
        synchronized (this.mutex) {
            this.vsyncSampler.removeObserver();
            if (this.shouldEnableAdaptiveRefreshSync) {
                this.displayManager.unregisterDisplayListener(this.displayListener);
                Handler handler = this.handler;
                Intrinsics.checkNotNull(handler);
                handler.getLooper().quitSafely();
            }
        }
    }

    public final void enable() {
        synchronized (this.mutex) {
            this.haveSync = false;
            this.vsyncSampler.addObserver();
            if (this.shouldEnableAdaptiveRefreshSync) {
                HandlerThread handlerThread = new HandlerThread("RefRateCalc");
                handlerThread.start();
                Handler handler = new Handler(handlerThread.getLooper());
                this.handler = handler;
                this.displayManager.registerDisplayListener(this.displayListener, handler);
            }
        }
    }

    public final boolean isDriftTooLarge(long j, long j2) {
        return Math.abs((j2 - this.syncUnadjustedReleaseTimeNs) - (j - this.syncFramePresentationTimeNs)) > 20000000;
    }
}
