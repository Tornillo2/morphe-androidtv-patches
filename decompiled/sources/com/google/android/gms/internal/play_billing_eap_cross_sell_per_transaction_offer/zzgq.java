package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgq {
    public static final /* synthetic */ int zza = 0;
    public static final zzgq zzb = new zzgq();
    public final ConcurrentMap zzd = new ConcurrentHashMap();
    public final zzgu zzc = new zzgb();

    public static zzgq zza() {
        return zzb;
    }

    public final zzgt zzb(Class cls) {
        zzfm.zzc(cls, "messageType");
        ConcurrentMap concurrentMap = this.zzd;
        zzgt zzgtVarZza = (zzgt) concurrentMap.get(cls);
        if (zzgtVarZza == null) {
            zzgtVarZza = this.zzc.zza(cls);
            zzgt zzgtVar = (zzgt) concurrentMap.putIfAbsent(cls, zzgtVarZza);
            if (zzgtVar != null) {
                return zzgtVar;
            }
        }
        return zzgtVarZza;
    }
}
