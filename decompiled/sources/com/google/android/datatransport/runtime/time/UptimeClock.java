package com.google.android.datatransport.runtime.time;

import android.os.SystemClock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class UptimeClock implements Clock {
    @Override // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return SystemClock.elapsedRealtime();
    }
}
