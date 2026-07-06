package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcb extends zzbv {
    public final transient zzbu zza;
    public final transient zzbr zzb;

    public zzcb(zzbu zzbuVar, zzbr zzbrVar) {
        this.zza = zzbuVar;
        this.zzb = zzbrVar;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbv, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbv, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public final zzbr zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbv, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    /* JADX INFO: renamed from: zze */
    public final zzcf iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public final boolean zzf() {
        throw null;
    }
}
