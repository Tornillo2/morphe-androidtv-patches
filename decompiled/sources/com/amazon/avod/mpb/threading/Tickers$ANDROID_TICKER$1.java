package com.amazon.avod.mpb.threading;

import android.os.SystemClock;
import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Tickers$ANDROID_TICKER$1 extends Ticker {
    @Override // com.google.common.base.Ticker
    public long read() {
        return TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
    }
}
