package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import j$.util.Objects;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgy implements Iterator {
    public final /* synthetic */ zzhb zza;
    public int zzb;
    public boolean zzc;
    public Iterator zzd;

    public /* synthetic */ zzgy(zzhb zzhbVar, zzha zzhaVar) {
        Objects.requireNonNull(zzhbVar);
        this.zza = zzhbVar;
        this.zzb = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb + 1;
        zzhb zzhbVar = this.zza;
        if (i >= zzhbVar.zzb) {
            return !zzhbVar.zzc.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        zzhb zzhbVar = this.zza;
        return i < zzhbVar.zzb ? (zzgx) zzhbVar.zza[i] : (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        zzhb zzhbVar = this.zza;
        zzhbVar.zzo();
        int i = this.zzb;
        if (i >= zzhbVar.zzb) {
            zza().remove();
        } else {
            this.zzb = i - 1;
            zzhbVar.zzm(i);
        }
    }

    public final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }
}
