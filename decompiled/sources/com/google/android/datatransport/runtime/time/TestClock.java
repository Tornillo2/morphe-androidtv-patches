package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TestClock implements Clock {
    public final AtomicLong timestamp;

    public TestClock(long j) {
        this.timestamp = new AtomicLong(j);
    }

    public void advance(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("cannot advance time backwards.");
        }
        this.timestamp.addAndGet(j);
    }

    @Override // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return this.timestamp.get();
    }

    public void tick() {
        advance(1L);
    }
}
