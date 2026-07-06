package com.amazon.avod.mpb.media.playback.avsync;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TimeSource$Companion$DEFAULT_INSTANCE$1 implements TimeSource {
    @Override // com.amazon.avod.mpb.media.playback.avsync.TimeSource
    public long getCurrentRealTimeUs() {
        return TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
    }

    @Override // com.amazon.avod.mpb.media.playback.avsync.TimeSource
    public boolean hasStartedTicking() {
        return true;
    }
}
