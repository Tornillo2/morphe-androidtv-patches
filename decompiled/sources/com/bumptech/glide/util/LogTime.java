package com.bumptech.glide.util;

import android.annotation.TargetApi;
import android.os.SystemClock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LogTime {
    public static final double MILLIS_MULTIPLIER = 1.0d / Math.pow(10.0d, 6.0d);

    public static double getElapsedMillis(long j) {
        return (SystemClock.elapsedRealtimeNanos() - j) * MILLIS_MULTIPLIER;
    }

    @TargetApi(17)
    public static long getLogTime() {
        return SystemClock.elapsedRealtimeNanos();
    }
}
