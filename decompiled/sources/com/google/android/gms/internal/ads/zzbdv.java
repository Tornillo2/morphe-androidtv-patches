package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbdv {
    public static final AtomicReference zzb = new AtomicReference();
    public static final AtomicReference zzc = new AtomicReference();
    public static final AtomicBoolean zza = new AtomicBoolean();

    public static zzbdt zza() {
        return (zzbdt) zzb.get();
    }

    public static zzbdu zzb() {
        return (zzbdu) zzc.get();
    }

    public static void zzc(zzbdt zzbdtVar) {
        zzb.set(zzbdtVar);
    }
}
