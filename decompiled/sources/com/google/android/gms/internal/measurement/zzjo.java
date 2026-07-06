package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjo {
    public static final zzjo zza = new zzjo(true);
    public static volatile boolean zzb = false;
    public static volatile zzjo zzc;
    public static volatile zzjo zzd;
    public final Map zze;

    public zzjo() {
        this.zze = new HashMap();
    }

    public static zzjo zza() {
        zzjo zzjoVar;
        zzjo zzjoVar2 = zzc;
        if (zzjoVar2 != null) {
            return zzjoVar2;
        }
        synchronized (zzjo.class) {
            try {
                zzjoVar = zzc;
                if (zzjoVar == null) {
                    zzjoVar = zza;
                    zzc = zzjoVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzjoVar;
    }

    public static zzjo zzb() {
        zzjo zzjoVar = zzd;
        if (zzjoVar != null) {
            return zzjoVar;
        }
        synchronized (zzjo.class) {
            try {
                zzjo zzjoVar2 = zzd;
                if (zzjoVar2 != null) {
                    return zzjoVar2;
                }
                zzjo zzjoVarZzb = zzjw.zzb(zzjo.class);
                zzd = zzjoVarZzb;
                return zzjoVarZzb;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzka zzc(zzlj zzljVar, int i) {
        return (zzka) this.zze.get(new zzjn(zzljVar, i));
    }

    public zzjo(boolean z) {
        this.zze = Collections.EMPTY_MAP;
    }
}
