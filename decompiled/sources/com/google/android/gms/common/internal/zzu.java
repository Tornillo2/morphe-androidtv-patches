package com.google.android.gms.common.internal;

import android.net.Uri;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzu {
    public static final Uri zza;
    public static final Uri zzb;

    static {
        Uri uri = Uri.parse("https://plus.google.com/");
        zza = uri;
        zzb = uri.buildUpon().appendPath("circles").appendPath("find").build();
    }
}
