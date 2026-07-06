package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbv extends zzbo implements Set, j$.util.Set {
    public transient zzbr zza;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    return containsAll(set);
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzce.zza(this);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo
    public zzbr zzd() {
        zzbr zzbrVar = this.zza;
        if (zzbrVar != null) {
            return zzbrVar;
        }
        zzbr zzbrVarZzh = zzh();
        this.zza = zzbrVarZzh;
        return zzbrVarZzh;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbo, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: zze */
    public abstract zzcf iterator();

    public zzbr zzh() {
        Object[] array = toArray();
        int i = zzbr.zzd;
        return zzbr.zzi(array, array.length);
    }
}
