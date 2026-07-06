package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzes {
    public static final zzes zza = new zzes(true);
    public static final /* synthetic */ int zzb = 0;
    public static volatile boolean zzc = false;
    public static volatile zzes zzd;
    public final Map zze;

    public zzes() {
        this.zze = new HashMap();
    }

    public static zzes zza() {
        zzes zzesVar = zzd;
        if (zzesVar != null) {
            return zzesVar;
        }
        synchronized (zzes.class) {
            try {
                zzes zzesVar2 = zzd;
                if (zzesVar2 != null) {
                    return zzesVar2;
                }
                int i = zzgq.zza;
                zzes zzesVarZzb = zzfa.zzb(zzes.class);
                zzd = zzesVarZzb;
                return zzesVarZzb;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzff zzb(zzgj zzgjVar, int i) {
        return (zzff) this.zze.get(new zzer(zzgjVar, i));
    }

    public zzes(boolean z) {
        this.zze = Collections.EMPTY_MAP;
    }
}
