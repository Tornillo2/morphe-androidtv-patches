package com.google.android.gms.internal.common;

import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzd {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 23 ? 67108864 : 0;
    }
}
