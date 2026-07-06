package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SystemClock implements Clock {
    @Override // com.google.android.exoplayer2.util.Clock
    public HandlerWrapper createHandler(Looper looper, @Nullable Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }

    @Override // com.google.android.exoplayer2.util.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.google.android.exoplayer2.util.Clock
    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.exoplayer2.util.Clock
    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    @Override // com.google.android.exoplayer2.util.Clock
    public void onThreadBlocked() {
    }
}
