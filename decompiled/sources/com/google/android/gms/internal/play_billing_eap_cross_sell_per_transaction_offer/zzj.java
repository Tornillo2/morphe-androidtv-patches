package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzj extends zzd {
    public final AtomicReferenceFieldUpdater zza;
    public final AtomicReferenceFieldUpdater zzb;
    public final AtomicReferenceFieldUpdater zzc;
    public final AtomicReferenceFieldUpdater zzd;
    public final AtomicReferenceFieldUpdater zze;

    public zzj(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.zza = atomicReferenceFieldUpdater;
        this.zzb = atomicReferenceFieldUpdater2;
        this.zzc = atomicReferenceFieldUpdater3;
        this.zzd = atomicReferenceFieldUpdater4;
        this.zze = atomicReferenceFieldUpdater5;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final void zza(zzm zzmVar, zzm zzmVar2) {
        this.zzb.lazySet(zzmVar, zzmVar2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final void zzb(zzm zzmVar, Thread thread) {
        this.zza.lazySet(zzmVar, thread);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final boolean zzc(zzo zzoVar, zzh zzhVar, zzh zzhVar2) {
        return zzi.zza(this.zzd, zzoVar, zzhVar, zzhVar2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final boolean zzd(zzo zzoVar, Object obj, Object obj2) {
        return zzi.zza(this.zze, zzoVar, obj, obj2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzd
    public final boolean zze(zzo zzoVar, zzm zzmVar, zzm zzmVar2) {
        return zzi.zza(this.zzc, zzoVar, zzmVar, zzmVar2);
    }
}
