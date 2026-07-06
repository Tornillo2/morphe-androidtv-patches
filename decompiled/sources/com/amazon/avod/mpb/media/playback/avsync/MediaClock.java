package com.amazon.avod.mpb.media.playback.avsync;

import com.amazon.avod.mpb.api.core.MpbLog;
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
@SourceDebugExtension({"SMAP\nMediaClock.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaClock.kt\ncom/amazon/avod/mpb/media/playback/avsync/MediaClock\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,107:1\n1#2:108\n*E\n"})
public final class MediaClock {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long UNSET = -1;

    @Nullable
    public TimeSource actualTimeSource;
    public final boolean mIsVerboseMediaClockLoggingEnabled;

    @NotNull
    public final Object mutex = new Object();
    public long startActualTimeUs = -1;
    public long startPresentationTimeUs = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public MediaClock(boolean z) {
        this.mIsVerboseMediaClockLoggingEnabled = z;
    }

    public final long getCurrentMediaTimeUs() {
        long currentRealTimeUs;
        synchronized (this.mutex) {
            long j = this.startPresentationTimeUs;
            if (j == -1) {
                currentRealTimeUs = 0;
            } else {
                TimeSource timeSource = this.actualTimeSource;
                Intrinsics.checkNotNull(timeSource);
                currentRealTimeUs = j + timeSource.getCurrentRealTimeUs();
            }
            if (this.mIsVerboseMediaClockLoggingEnabled) {
                TimeUnit timeUnit = TimeUnit.MICROSECONDS;
                MpbLog.logf("MediaClock currentMediaTimeMs: " + timeUnit.toMillis(currentRealTimeUs) + ", startPresentationTimeMs: " + timeUnit.toMillis(this.startPresentationTimeUs), new Object[0]);
            }
        }
        return currentRealTimeUs;
    }

    public final boolean hasActualTimeSource() {
        boolean z;
        synchronized (this.mutex) {
            z = this.actualTimeSource != null;
        }
        return z;
    }

    public final void setTimeSource(@NotNull TimeSource timeSource) {
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        synchronized (this.mutex) {
            if (this.startActualTimeUs != -1) {
                throw new IllegalStateException("Must call setTimeSource(..) before start(..)!");
            }
            this.actualTimeSource = timeSource;
        }
    }

    public final void start(long j) {
        synchronized (this.mutex) {
            TimeSource timeSource = this.actualTimeSource;
            Intrinsics.checkNotNull(timeSource);
            long currentRealTimeUs = timeSource.getCurrentRealTimeUs();
            this.startActualTimeUs = currentRealTimeUs;
            this.startPresentationTimeUs = j;
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            MpbLog.logf("MediaClock start, startActualTimeMs: " + timeUnit.toMillis(currentRealTimeUs) + " startPresentationTimeMs: " + timeUnit.toMillis(this.startPresentationTimeUs), new Object[0]);
        }
    }

    public final long stop(boolean z) {
        long currentMediaTimeUs;
        synchronized (this.mutex) {
            try {
                currentMediaTimeUs = getCurrentMediaTimeUs();
                this.startActualTimeUs = -1L;
                this.startPresentationTimeUs = -1L;
                if (z) {
                    this.actualTimeSource = null;
                }
                MpbLog.logf("MediaClock stop, currentMediaTimeMs: " + TimeUnit.MICROSECONDS.toMillis(currentMediaTimeUs), new Object[0]);
            } catch (Throwable th) {
                throw th;
            }
        }
        return currentMediaTimeUs;
    }
}
