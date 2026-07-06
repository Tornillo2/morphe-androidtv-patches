package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import j$.util.Objects;
import java.util.AbstractMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbz extends zzbr {
    public final /* synthetic */ zzca zza;

    public zzbz(zzca zzcaVar) {
        Objects.requireNonNull(zzcaVar);
        this.zza = zzcaVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzca zzcaVar = this.zza;
        zzbe.zza(i, zzcaVar.zzc, "index");
        int i2 = i + i;
        Object obj = zzcaVar.zzb[i2];
        Objects.requireNonNull(obj);
        Object obj2 = zzcaVar.zzb[i2 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public final boolean zzf() {
        return true;
    }
}
