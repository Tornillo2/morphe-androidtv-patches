package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzca extends zzbv {
    public final transient zzbu zza;
    public final transient Object[] zzb;
    public final transient int zzc;

    public zzca(zzbu zzbuVar, Object[] objArr, int i, int i2) {
        this.zza = zzbuVar;
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbv, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public final int zza(Object[] objArr, int i) {
        return zzd().zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbv, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    /* JADX INFO: renamed from: zze */
    public final zzcf iterator() {
        return zzd().listIterator(0);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbv
    public final zzbr zzh() {
        return new zzbz(this);
    }
}
