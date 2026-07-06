package com.google.android.gms.internal.location;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbj {
    public static Looper zza(@Nullable Looper looper) {
        return looper != null ? looper : zzb();
    }

    public static Looper zzb() {
        Preconditions.checkState(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }
}
