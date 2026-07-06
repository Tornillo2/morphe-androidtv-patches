package com.google.android.gms.internal.ads;

import java.io.Closeable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzfle implements Closeable {
    public static zzflq zza() {
        return new zzflq();
    }

    public static zzflq zzb(final int i, zzflp zzflpVar) {
        return new zzflq(new zzfpp() { // from class: com.google.android.gms.internal.ads.zzflc
            @Override // com.google.android.gms.internal.ads.zzfpp
            public final Object zza() {
                return Integer.valueOf(i);
            }
        }, zzfld.zza, zzflpVar);
    }

    public static zzflq zzc(zzfpp<Integer> zzfppVar, zzfpp<Integer> zzfppVar2, zzflp zzflpVar) {
        return new zzflq(zzfppVar, zzfppVar2, zzflpVar);
    }

    public static /* synthetic */ Integer zze() {
        return -1;
    }
}
