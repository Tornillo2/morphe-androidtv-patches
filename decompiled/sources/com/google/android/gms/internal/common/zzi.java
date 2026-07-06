package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzi extends Handler {
    public final Looper zza;

    public zzi() {
        this.zza = Looper.getMainLooper();
    }

    public zzi(Looper looper) {
        super(looper);
        this.zza = Looper.getMainLooper();
    }

    public zzi(Looper looper, Handler.Callback callback) {
        super(looper, callback);
        this.zza = Looper.getMainLooper();
    }
}
