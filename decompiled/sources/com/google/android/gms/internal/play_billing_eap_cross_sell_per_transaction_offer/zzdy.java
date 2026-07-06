package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import j$.util.Objects;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdy extends zzdz {
    public final /* synthetic */ zzeg zza;
    public int zzb;
    public final int zzc;

    public zzdy(zzeg zzegVar) {
        Objects.requireNonNull(zzegVar);
        this.zza = zzegVar;
        this.zzb = 0;
        this.zzc = zzegVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeb
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
