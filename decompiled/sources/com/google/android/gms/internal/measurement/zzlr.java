package com.google.android.gms.internal.measurement;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzlr {
    public static final zzlr zza = new zzlr();
    public final ConcurrentMap zzc = new ConcurrentHashMap();
    public final zzlv zzb = new zzlb();

    public static zzlr zza() {
        return zza;
    }

    public final zzlu zzb(Class cls) {
        zzkk.zzf(cls, "messageType");
        zzlu zzluVar = (zzlu) this.zzc.get(cls);
        if (zzluVar != null) {
            return zzluVar;
        }
        zzlu zzluVarZza = this.zzb.zza(cls);
        zzlu zzluVar2 = (zzlu) this.zzc.putIfAbsent(cls, zzluVarZza);
        return zzluVar2 == null ? zzluVarZza : zzluVar2;
    }
}
